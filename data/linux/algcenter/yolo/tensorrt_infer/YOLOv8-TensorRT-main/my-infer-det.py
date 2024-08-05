from models import TRTModule  # isort:skip
import argparse
from pathlib import Path

import cv2
import torch
import time
import subprocess
import sys

from config import CLASSES_DET, COLORS
from models.torch_utils import det_postprocess
from models.utils import blob, letterbox, path_to_list


def main(args: argparse.Namespace) -> None:
    device = torch.device(args.device)
    Engine = TRTModule(args.engine, device)
    H, W = Engine.inp_info[0].shape[-2:]

    # set desired output names order
    Engine.set_desired(['num_dets', 'bboxes', 'scores', 'labels'])

    #images = path_to_list(args.imgs)
    save_path = Path(args.out_dir)

    if not args.show and not save_path.exists():
        save_path.mkdir(parents=True, exist_ok=True)

    # 输入视频路径
    # input_video_path = 'car3.mp4'
    input_video_path = 'https://qbj.jszhcs.cn:9589/dj-cloud-bucket/wayline/e864c152-240e-4250-ac2e-8b8f2252997e/DJI_202403131744_006_e864c152-240e-4250-ac2e-8b8f2252997e/DJI_20240313174739_0002_Z.mp4'
    # 输出视频路径
    output_video_path = 'rtmp://192.168.2.241:11935/stream/live/dec_tensor333?sign=41db35390ddad33f83944f44b8b75ded'
    # 打开视频文件
    cap = cv2.VideoCapture(input_video_path)

    # 视频属性
    total_frame = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
    height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
    size = (width, height)
    sizeStr = str(size[0]) + 'x' + str(size[1])
    fps = cap.get(cv2.CAP_PROP_FPS)  # 30p/self
    fps = int(fps)
    hz = int(1000.0 / fps)
    print('size:'+ sizeStr + ' fps:' + str(fps) + ' hz:' + str(hz) + ' total_frame: ' + str(total_frame))

    # (1).推送实时计算的画面
    command = [
    'ffmpeg',
    '-re',
    '-y',
    '-stream_loop', '-1',
    '-f', 'rawvideo',
    '-vcodec','rawvideo',
    '-pix_fmt', 'bgr24',
    '-s', sizeStr,
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
    output_video_path]
    pipe = subprocess.Popen(command, stdin=subprocess.PIPE)
  
    # (2).推送原始视频的画面
    index = output_video_path.index("dec_")
    output_video_path_ori = output_video_path[0:index] + "ori" + output_video_path[index + 3:]
    command_ori = [
    'ffmpeg',
    '-re',
    '-y',
    '-stream_loop', '-1',
    '-f', 'rawvideo',
    '-vcodec','rawvideo',
    '-pix_fmt', 'bgr24',
    '-s', sizeStr,
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
    output_video_path_ori]
    pipe_ori = subprocess.Popen(command_ori, stdin=subprocess.PIPE)


    # 检查视频是否成功打开
    if not cap.isOpened():
        print("Error: Could not open video.")
        exit()
    # 读取视频的基本信息
    frame_width = int(cap.get(3))
    frame_height = int(cap.get(4))
    fps = cap.get(cv2.CAP_PROP_FPS)
    # 定义视频编码器和创建VideoWriter对象
    #fourcc = cv2.VideoWriter_fourcc(*'mp4v')  # 根据文件名后缀使用合适的编码器
    #out = cv2.VideoWriter(output_video_path, fourcc, fps, (frame_width, frame_height))
    # 初始化帧数计数器和起始时间
    frame_count = 0
    start_time = time.time()

    # 跳帧检测
    detect_skip_index = 1
    detect_skip = 6
    if fps > 25:
        detect_skip = 8

    while True:
        ret, frame = cap.read()
        if not ret:
            print("End of video file,current frame: ",str(frame_count))
            break
        start_time = time.time()
        
      
        # 帧数读完时将帧位置重置为0，循环读取
        frame_count += 1
        if frame_count == total_frame:
            frame_count = 0
            cap.set(cv2.CAP_PROP_POS_FRAMES, 0)

        # 预处理
        draw = frame.copy()
        frame, ratio, dwdh = letterbox(frame, (W, H))
        rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
        tensor = blob(rgb, return_seg=False)
        dwdh = torch.asarray(dwdh * 2, dtype=torch.float32, device=device)
        tensor = torch.asarray(tensor, device=device)
        # inference
        data = Engine(tensor)

        bboxes, scores, labels = det_postprocess(data)
        if bboxes.numel() == 0:
            # if no bounding box
            #print(f'{frame}: no object!')
            continue
        bboxes -= dwdh
        bboxes /= ratio

        # 推送原始视频
        pipe_ori.stdin.write(draw.tobytes())

        for (bbox, score, label) in zip(bboxes, scores, labels):
            bbox = bbox.round().int().tolist()
            cls_id = int(label)
            cls = CLASSES_DET[cls_id]
            color = COLORS[cls]

            text = f'{cls}:{score:.3f}'
            x1, y1, x2, y2 = bbox

            (_w, _h), _bl = cv2.getTextSize(text, cv2.FONT_HERSHEY_SIMPLEX, 0.8, 1)
            _y1 = min(y1 + 1, draw.shape[0])

            cv2.rectangle(draw, (x1, y1), (x2, y2), color, 2)
            cv2.rectangle(draw, (x1, _y1), (x1 + _w, _y1 + _h + _bl), (0, 0, 255), -1)
            cv2.putText(draw, text, (x1, _y1 + _h), cv2.FONT_HERSHEY_SIMPLEX, 0.75, (255, 255, 255), 2)

        end_time = time.time()
        #print("per frame dectect cost time:",str((end_time - start_time)*1000),"ms")        

        if args.show:
            cv2.imshow('result', draw)
            cv2.waitKey(0)
        else:
            pipe.stdin.write(draw.tobytes())

    # 释放资源
    cap.release()
    cv2.destroyAllWindows()

def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser()
    parser.add_argument('--engine', type=str, help='Engine file')
    parser.add_argument('--imgs', type=str, help='Images file')
    parser.add_argument('--show',
                        action='store_true',
                        help='Show the detection results')
    parser.add_argument('--out-dir',
                        type=str,
                        default='./output',
                        help='Path to output file')
    parser.add_argument('--device',
                        type=str,
                        default='cuda:0',
                        help='TensorRT infer device')
    args = parser.parse_args()
    return args


if __name__ == '__main__':
    args = parse_args()
    main(args)
