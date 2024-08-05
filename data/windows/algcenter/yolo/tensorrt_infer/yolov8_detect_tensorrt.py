from models import TRTModule
from gsis_util import push_minio_rocketmq
import argparse
from pathlib import Path
import concurrent.futures
import schedule
import numpy as np
from datetime import datetime
from PIL import Image, ImageDraw, ImageFont

import cv2
import torch
import time
import subprocess
import sys

from config import CLASSES_DET, COLORS, CLASSES_DET_SMOKE, COLORS_SMOKE, CLASSES_DET_SAFETY_HAT, COLORS_SAFETY_HAT, CLASSES_DET_EBIKE, COLORS_EBIKE, \
    COLORS_FIRE, CLASSES_DET_FIRE, COLORS_CRACK, CLASSES_DET_CRACK, COLORS_CAR_PLATE,CLASSES_DET_CAR_PLATE, COLORS_BARE_SOIL, CLASSES_DET_BARE_SOIL, \
    CLASSES_DET_RIVER_REFUSE, COLORS_RIVER_REFUSE, CLASSES_DET_PLANT,COLORS_PLANT

from models.torch_utils import det_postprocess
from models.utils import blob, letterbox, path_to_list

# 自定义线程池
pool = concurrent.futures.ThreadPoolExecutor(max_workers=8)

# 自定义定时器，每隔60秒执行一次
push_flag = 0;
def exec_schedule():
    # 时间间隔(60秒只推送1次)
    def reset_push_flag():
        global push_flag
        push_flag = 1
    schedule.every(args.push_frq).seconds.do(reset_push_flag)
    while True:
        schedule.run_pending()
        time.sleep(args.push_frq)



def compute_overlap_area(box1, box2):
    x1 = max(box1[0], box2[0])
    y1 = max(box1[1], box2[1])
    x2 = min(box1[0] + box1[2], box2[0] + box2[2])
    y2 = min(box1[1] + box1[3], box2[1] + box2[3])

    width = max(0, x2 - x1)
    height = max(0, y2 - y1)

    overlap_area = width * height
    return overlap_area

def is_box_inside(box1, box2):
    box1_area = box1[2] * box1[3]
    overlap_area = compute_overlap_area(box1, box2)

    if overlap_area >= 0.5 * box1_area:
        return True
    else:
        return False

def cv2ImgPutChineseText(img, text, position, textColor=(255, 255, 255), textSize=20):
    if (isinstance(img, np.ndarray)):  # 判断是否OpenCV图片类型
        img = Image.fromarray(cv2.cvtColor(img, cv2.COLOR_BGR2RGB))
    # 创建一个可以在给定图像上绘图的对象
    draw = ImageDraw.Draw(img)
    # 字体的格式
    fontStyle = ImageFont.truetype(
        "simsun.ttc", textSize, encoding="utf-8")
    # 绘制文本
    draw.text(position, text, textColor, font=fontStyle)
    # 转换回OpenCV格式
    return cv2.cvtColor(np.asarray(img), cv2.COLOR_RGB2BGR)

def main(args: argparse.Namespace) -> None:
    # 1.加载tensorRT模型
    # (1).通过pytorch加载运行推理的GPU
    device = torch.device(args.device)

    # (2).加载模型引擎并获取模型的高和宽
    Engine = TRTModule(args.engine, device)
    H, W = Engine.inp_info[0].shape[-2:]

    # (3).设置想要的输出数据及顺序
    Engine.set_desired(['num_dets', 'bboxes', 'scores', 'labels'])

    # 2.视频地址和流媒体服务器地址
    video_url = args.video_url
    stream_server_url = args.stream_server_url


    # 3.处理视频
    # (1).打开视频
    cap = cv2.VideoCapture(video_url)
    if not cap.isOpened():
        print("Error: Could not open video")
        exit()

    # (2).视频属性
    total_frame = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
    height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
    size_str = str(width) + 'x' + str(height)
    fps = cap.get(cv2.CAP_PROP_FPS)
    print('step000 ------>  size:' + size_str + ' fps:' + str(fps) + ' total_frame: ' + str(total_frame))

    # (3).获取到ffmpeg推送视频流的pipe
    stream_server_url_ori = stream_server_url[0:stream_server_url.index("dec_")] \
                            + "ori" \
                            + stream_server_url[stream_server_url.index("dec_") + 3:]
    pipe_dec = get_ffmpeg_pipe(size_str, fps, stream_server_url)
    pipe_ori = get_ffmpeg_pipe(size_str, fps, stream_server_url_ori)

    # (4).初始化帧数计数器和起始时间
    frame_count = 0
    start_time = time.time()

    # (5).跳帧检测
    detect_skip_index = 0
    detect_skip = args.skip_frame

    # (6).任务编号
    task_no = stream_server_url[stream_server_url.index("dec_") + 4:stream_server_url.index("dec_") + 12]

    # (7).rocketmq的topic(默认为inference_result) 和 group_name(默认为gsis_group1)
    topic = 'inference_result'
    group_name = 'gsis_group1'

    # (8).minio的桶名称
    model_prefixx = args.engine.split(".")[0]
    bucket_name = 'algcenter-' + model_prefixx.replace('_', '-')

    # (9).开启新的线程执行定时任务(每隔60秒更新一次push_flag为1，以便每隔60秒保存识别图片一次)
    pool.submit(exec_schedule)

    # (10).分类id及对应的最高评分
    cls_score = {}

    cls_text_coord = {}

    # (11).循环读取视频每一帧
    while True:
        ret, frame = cap.read()
        if not ret:
            print("End of video file,current frame: ", str(frame_count))
            break

        # --1.帧数读完时将帧索引重置为0，循环读取
        start_time = time.time()
        frame_count += 1
        if frame_count == total_frame:
            frame_count = 0
            cap.set(cv2.CAP_PROP_POS_FRAMES, 0)

        # --2.跳帧检测
        detect_skip_index += 1
        if (detect_skip_index % detect_skip != 0):
            pipe_ori.stdin.write(frame.tobytes())
            pipe_dec.stdin.write(frame.tobytes())
            continue

        # --3.帧数据预处理(ori_draw:原始画面，draw:写入label后的检测画面，frame:用于转换为张量tensor)
        ori_draw = frame.copy()
        draw = frame.copy()
        frame, ratio, dwdh = letterbox(frame, (W, H))
        rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
        tensor = blob(rgb, return_seg=False)
        dwdh = torch.asarray(dwdh * 2, dtype=torch.float32, device=device)
        tensor = torch.asarray(tensor, device=device)

        # --4.执行推理
        data = Engine(tensor)

        # --5.获取到推理结果
        bboxes, scores, labels = det_postprocess(data)

        # --6.当前帧没检测出目标，直接推送原始视频帧，然后continue计算下一帧
        if bboxes.numel() == 0:
            cls_score.clear()
            # 当前帧没检测出目标，直接推送原始视频帧
            pipe_ori.stdin.write(ori_draw.tobytes())
            pipe_dec.stdin.write(draw.tobytes())
            continue
        bboxes -= dwdh
        bboxes /= ratio

        # --7.对视频帧进行画框并通过ffmpeg推送检测结果视频帧到流媒体服务器
        for (bbox, score, label) in zip(bboxes, scores, labels):
            # (1).目标边界框
            bbox = bbox.round().int().tolist()
            x1, y1, x2, y2 = bbox

            # (2).目标分类id及颜色
            cls_id = int(label)
            cls = CLASSES_DET[cls_id]
            color = COLORS[cls]

            if args.engine == 'yolov8_sl_fh.engine':
                cls = CLASSES_DET_SMOKE[cls_id]
                color = COLORS_SMOKE[cls]

            if args.engine == 'yolov8_safety_hat.engine':
                # 只给索引为[x,x,x]的分类画框,即自定义某些分类不用显示在画面上
                if cls_id not in [0,1]:
                    continue
                cls = CLASSES_DET_SAFETY_HAT[cls_id]
                color = COLORS_SAFETY_HAT[cls]

            if args.engine == 'ebike.engine':
                if cls_id not in [0,1,2]:
                    continue
                cls = CLASSES_DET_EBIKE[cls_id]
                color = COLORS_EBIKE[cls]

            if args.engine == 'yolov8s-fs.engine':
                if cls_id not in [0, 1]:
                    continue
                cls = CLASSES_DET_FIRE[cls_id]
                color = COLORS_FIRE[cls]

            if args.engine == 'crack.engine':
                if cls_id not in [0,1,2,3,4,5]:
                    continue
                cls = CLASSES_DET_CRACK[cls_id]
                color = COLORS_CRACK[cls]

            if args.engine == 'bare_soil.engine':
                if cls_id not in [0]:
                    continue
                cls = CLASSES_DET_BARE_SOIL[cls_id]
                color = COLORS_BARE_SOIL[cls]

            if args.engine == 'car_plate_rec_yolov8s.engine':
                if cls_id not in [0, 1, 2, 3, 4, 5]:
                    continue
                cls = CLASSES_DET_CAR_PLATE[cls_id]
                color = COLORS_CAR_PLATE[cls]
                #current_text = f'{cls}:{score:.3f}'
                #current_text_coord = {current_text:bbox}
                #cls_text_coord.update({cls_id:current_text_coord})

            if args.engine == 'river.engine':
                if cls_id not in [0]:
                    continue
                cls = CLASSES_DET_RIVER_REFUSE[cls_id]
                color = COLORS_RIVER_REFUSE[cls]

            if args.engine == 'poppy.engine':
                if cls_id not in [0,1]:
                    continue
                cls = CLASSES_DET_PLANT[cls_id]
                color = COLORS_PLANT[cls]


            # (3).分类的文字及评分
            text = f'{cls}:{score:.3f}'

            # (4).获取到标签文字的尺寸
            (_w, _h), _bl = cv2.getTextSize(text, cv2.FONT_HERSHEY_SIMPLEX, 0.8, 1)
            _y1 = min(y1 + 1, draw.shape[0])

            # (5).将矩形框绘制到图像的目标上
            cv2.rectangle(draw, (x1, y1), (x2, y2), color, 2)

            # (6).在画框内再次绘制一个红色矩形背景框作为文字的背景( _y1 + _h + _bl = 背景框放在目标框内; _y1 - _h - _bl = 背景框放在目标框外)
            cv2.rectangle(draw, (x1, _y1), (x1 + _w, _y1 - _h), (0, 0, 255), -1)

            # (7).将文字绘制到红色矩形背景框上( _y1 + _h = 文字放在目标框内; _y1 - _h = 文字放在目标框外)
            # --1.绘制标签(只支持英文)
            #cv2.putText(draw, text, (x1, _y1), cv2.FONT_HERSHEY_SIMPLEX, 0.75, (255, 255, 255), 2)

            # --2.绘制标签(支持英文和中文)
            draw = cv2ImgPutChineseText(draw, text, (x1, _y1 - _h), (255, 255, 255), 20)


            # (8).获取到评分分数，保留小数点后3位
            score_num = np.round(score.item(), decimals=3)
            cls_score.update({cls: score_num})

            # 从字典获取到当前分类的最高评分，如果当前评分高于最高评分则更新最高评分
            #max_score_num = cls_maxscore.get(str(cls_id))
            #if max_score_num is None or max_score_num == "":
            #cls_maxscore.update({str(cls_id): score_num})
            #else:
            #max_score_num = max(max_score_num,score_num)
            #cls_maxscore.update({str(cls_id): max_score_num})
            #print('step000 ---> cls_id, score, max_score', cls_id, score_num, max_score_num)

        # 如果小的边界框在大的边界框内部，则文字不覆盖而是一横排全部展示
        #if args.engine == 'ebike.engine':
            # 0分类和1分类均在同一张图片上
            #if 0 in cls_text_coord and 1 in cls_text_coord:
                #text_coord_0 = cls_text_coord.get(0)
                #text_0 = list(text_coord_0.keys())[0]
                #bbox_0 = text_coord_0[text_0]

                #text_coord_1 = cls_text_coord.get(1)
                #text_1 = list(text_coord_1.keys())[0]
                #bbox_1 = text_coord_1[text_1]

                #inside = is_box_inside(bbox_0,bbox_1)
                #if inside:
                    #x1_mix, y1_mix, x2_mix, y2_mix = bbox_0
                    #text_mix = text_0 + text_1
                    #(_w_mix, _h_mix),_bl = cv2.getTextSize(text_mix, cv2.FONT_HERSHEY_SIMPLEX, 0.8, 1)
                    #_y1_mix = min(y1 + 1, draw.shape[0])
                    #cv2.rectangle(draw, (x1_mix, _y1_mix), (x1_mix + _w_mix, _y1_mix - _h_mix), (0, 0, 255), -1)
                    #cv2.putText(draw, text_mix, (x1_mix, _y1_mix), cv2.FONT_HERSHEY_SIMPLEX, 0.75, (255, 255, 255), 2)

        # --8.推送原始视频和检测画面到流媒体服务器
        pipe_ori.stdin.write(ori_draw.tobytes())
        pipe_dec.stdin.write(draw.tobytes())
        #print("per frame detection cost time:",str((time.time() - start_time)*1000),"ms")

        # --9.所有的目标分类遍历完也没有找到想要的目标，不推送minio_rocketmq，继续处理下一帧
        if len(cls_score) == 0:
            cls_score.clear()
            continue

        # --10.开启新的线程推送检测画面到minio和rocketmq
        global push_flag
        if push_flag == 1:
            print('step1 ---> 开始推送数据到 minio_rocketmq, 分类评分(cls_score) --- 分类坐标cls_text_coord ', str(cls_score), '---', str(cls_text_coord), '---', time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
            date_str_s = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            img_file_name = task_no + '_' + datetime.now().strftime("%Y-%m-%d_%H:%M:%S_%f") + ".jpg"
            msg_body = {
                "task_no": task_no,
                "model_name": args.engine,
                "img_file_name": img_file_name,
                "bucket_name": bucket_name,
                "cls_score": str(cls_score),
                "alarm_time": date_str_s
            }
            pool.submit(push_minio_rocketmq, draw, img_file_name, bucket_name, group_name, topic, msg_body)
            push_flag = 0

        # --11.重置字典cls_score，以便记录下一帧画面的分类及评分
        cls_score.clear()

    # (12).读取结束,释放资源
    cap.release()
    cv2.destroyAllWindows()


def get_ffmpeg_pipe(size_str, fps, stream_server_url):
    ffmpeg_command = [
        'ffmpeg',
        '-re',
        '-y',
        '-stream_loop', '-1',
        '-f', 'rawvideo',
        '-vcodec', 'rawvideo',
        '-pix_fmt', 'bgr24',
        '-s', size_str,
        '-r', str(fps),
        '-i', '-',
        '-c:v', 'libx264',
        '-an',
        '-crf', '32',
        '-b:v', '1200k',
        '-bf', '0',
        '-g', str(fps),
        '-pix_fmt', 'yuv420p',
        '-preset', 'ultrafast',
        '-f', 'flv',
        stream_server_url]
    pipe = subprocess.Popen(ffmpeg_command, stdin=subprocess.PIPE)
    return pipe

def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser()
    parser.add_argument('--engine', type=str, required=False, default='yolov8n.engine', help='Engine file')
    parser.add_argument('--video_url', type=str, required=False, default='https://qbj.jszhcs.cn:9589/dj-cloud-bucket/wayline/e864c152-240e-4250-ac2e-8b8f2252997e/DJI_202403131744_006_e864c152-240e-4250-ac2e-8b8f2252997e/DJI_20240313174739_0002_Z.mp4', help='Video url or file')
    parser.add_argument('--stream_server_url', type=str, required=False, default='rtmp://192.168.2.241:11935/stream/live/dec_tensor01?sign=41db35390ddad33f83944f44b8b75ded', help='stream server url')
    parser.add_argument('--device',type=str,default='cuda:0',help='TensorRT infer device')
    parser.add_argument('--skip_frame',type=int,default='1',help='Skip Frame')
    parser.add_argument('--push_frq',type=int,default='40',help='Push Frequency')
    args = parser.parse_args()
    return args

if __name__ == '__main__':
    args = parse_args()
    main(args)
