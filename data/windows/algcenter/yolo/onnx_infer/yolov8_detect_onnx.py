import cv2
import onnxruntime as ort
from PIL import Image
import numpy as np
import sys
import time
import subprocess
import argparse
import concurrent.futures
import schedule


# 自定义线程池
pool = concurrent.futures.ThreadPoolExecutor(max_workers=4)
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


# 置信度
confidence_thres = 0.35
# iou阈值
iou_thres = 0.5
# 类别
classes = {0: 'person', 1: 'bicycle', 2: 'car', 3: 'motorcycle', 4: 'airplane', 5: 'bus', 6: 'train', 7: 'truck',
           8: 'boat', 9: 'traffic light', 10: 'fire hydrant', 11: 'stop sign', 12: 'parking meter', 13: 'bench',
           14: 'bird', 15: 'cat', 16: 'dog', 17: 'horse', 18: 'sheep', 19: 'cow', 20: 'elephant', 21: 'bear',
           22: 'zebra', 23: 'giraffe', 24: 'backpack', 25: 'umbrella', 26: 'handbag', 27: 'tie', 28: 'suitcase',
           29: 'frisbee', 30: 'skis', 31: 'snowboard', 32: 'sports ball', 33: 'kite', 34: 'baseball bat',
           35: 'baseball glove', 36: 'skateboard', 37: 'surfboard', 38: 'tennis racket', 39: 'bottle',
           40: 'wine glass', 41: 'cup', 42: 'fork', 43: 'knife', 44: 'spoon', 45: 'bowl', 46: 'banana', 47: 'apple',
           48: 'sandwich', 49: 'orange', 50: 'broccoli', 51: 'carrot', 52: 'hot dog', 53: 'pizza', 54: 'donut',
           55: 'cake', 56: 'chair', 57: 'couch', 58: 'potted plant', 59: 'bed', 60: 'dining table', 61: 'toilet',
           62: 'tv', 63: 'laptop', 64: 'mouse', 65: 'remote', 66: 'keyboard', 67: 'cell phone', 68: 'microwave',
           69: 'oven', 70: 'toaster', 71: 'sink', 72: 'refrigerator', 73: 'book', 74: 'clock', 75: 'vase',
           76: 'scissors', 77: 'teddy bear', 78: 'hair drier', 79: 'toothbrush'}
# 随机颜色
color_palette = np.random.uniform(100, 255, size=(len(classes), 3))

# 判断是使用GPU或CPU
providers = [
    ('CUDAExecutionProvider', {
        'device_id': 0,  # 可以选择GPU设备ID，如果你有多个GPU
    }),
    'CPUExecutionProvider',  # 也可以设置CPU作为备选
]

def calculate_iou(box, other_boxes):
    """
    计算给定边界框与一组其他边界框之间的交并比（IoU）。

    参数：
    - box: 单个边界框，格式为 [x1, y1, width, height]。
    - other_boxes: 其他边界框的数组，每个边界框的格式也为 [x1, y1, width, height]。

    返回值：
    - iou: 一个数组，包含给定边界框与每个其他边界框的IoU值。
    """

    # 计算交集的左上角坐标
    x1 = np.maximum(box[0], np.array(other_boxes)[:, 0])
    y1 = np.maximum(box[1], np.array(other_boxes)[:, 1])
    # 计算交集的右下角坐标
    x2 = np.minimum(box[0] + box[2], np.array(other_boxes)[:, 0] + np.array(other_boxes)[:, 2])
    y2 = np.minimum(box[1] + box[3], np.array(other_boxes)[:, 1] + np.array(other_boxes)[:, 3])
    # 计算交集区域的面积
    intersection_area = np.maximum(0, x2 - x1) * np.maximum(0, y2 - y1)
    # 计算给定边界框的面积
    box_area = box[2] * box[3]
    # 计算其他边界框的面积
    other_boxes_area = np.array(other_boxes)[:, 2] * np.array(other_boxes)[:, 3]
    # 计算IoU值
    iou = intersection_area / (box_area + other_boxes_area - intersection_area)
    return iou

def custom_NMSBoxes(boxes, scores, confidence_threshold, iou_threshold):
    # 如果没有边界框，则直接返回空列表
    if len(boxes) == 0:
        return []
    # 将得分和边界框转换为NumPy数组
    scores = np.array(scores)
    boxes = np.array(boxes)
    # 根据置信度阈值过滤边界框
    mask = scores > confidence_threshold
    filtered_boxes = boxes[mask]
    filtered_scores = scores[mask]
    # 如果过滤后没有边界框，则返回空列表
    if len(filtered_boxes) == 0:
        return []
    # 根据置信度得分对边界框进行排序
    sorted_indices = np.argsort(filtered_scores)[::-1]
    # 初始化一个空列表来存储选择的边界框索引
    indices = []
    # 当还有未处理的边界框时，循环继续
    while len(sorted_indices) > 0:
        # 选择得分最高的边界框索引
        current_index = sorted_indices[0]
        indices.append(current_index)
        # 如果只剩一个边界框，则结束循环
        if len(sorted_indices) == 1:
            break
        # 获取当前边界框和其他边界框
        current_box = filtered_boxes[current_index]
        other_boxes = filtered_boxes[sorted_indices[1:]]
        # 计算当前边界框与其他边界框的IoU
        iou = calculate_iou(current_box, other_boxes)
        # 找到IoU低于阈值的边界框，即与当前边界框不重叠的边界框
        non_overlapping_indices = np.where(iou <= iou_threshold)[0]
        # 更新sorted_indices以仅包含不重叠的边界框
        sorted_indices = sorted_indices[non_overlapping_indices + 1]
    # 返回选择的边界框索引
    return indices


def draw_detections(img, box, score, class_id):
    """
    在输入图像上绘制检测到的对象的边界框和标签。

    参数:
            img: 要在其上绘制检测结果的输入图像。
            box: 检测到的边界框。
            score: 对应的检测得分。
            class_id: 检测到的对象的类别ID。

    返回:
            无
    """

    # 提取边界框的坐标
    x1, y1, w, h = box
    # 根据类别ID检索颜色
    color = color_palette[class_id]
    # 在图像上绘制边界框
    cv2.rectangle(img, (int(x1), int(y1)), (int(x1 + w), int(y1 + h)), color, 2)
    # 创建标签文本，包括类名和得分
    label = f'{classes[class_id]}: {score:.2f}'
    # 计算标签文本的尺寸
    (label_width, label_height), _ = cv2.getTextSize(label, cv2.FONT_HERSHEY_SIMPLEX, 0.5, 1)
    # 计算标签文本的位置
    label_x = x1
    label_y = y1 - 10 if y1 - 10 > label_height else y1 + 10
    # 绘制填充的矩形作为标签文本的背景
    cv2.rectangle(img, (label_x, label_y - label_height), (label_x + label_width, label_y + label_height), color, cv2.FILLED)
    # 在图像上绘制标签文本
    cv2.putText(img, label, (label_x, label_y), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1, cv2.LINE_AA)


def preprocess(img, input_width, input_height):
    """
    在执行推理之前预处理输入图像。

    返回:
        image_data: 为推理准备好的预处理后的图像数据。
    """

    # 获取输入图像的高度和宽度
    img_height, img_width = img.shape[:2]
    # 将图像颜色空间从BGR转换为RGB
    img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    # 将图像大小调整为匹配输入形状
    img = cv2.resize(img, (input_width, input_height))
    # 通过除以255.0来归一化图像数据
    image_data = np.array(img) / 255.0
    # 转置图像，使通道维度为第一维
    image_data = np.transpose(image_data, (2, 0, 1))  # 通道首
    # 扩展图像数据的维度以匹配预期的输入形状
    image_data = np.expand_dims(image_data, axis=0).astype(np.float32)
    # 返回预处理后的图像数据
    return image_data, img_height, img_width

def postprocess(input_image, output, input_width, input_height, img_width, img_height):
    """
    对模型输出进行后处理，提取边界框、得分和类别ID。

    参数:
        input_image (numpy.ndarray): 输入图像。
        output (numpy.ndarray): 模型的输出。
        input_width (int): 模型输入宽度。
        input_height (int): 模型输入高度。
        img_width (int): 原始图像宽度。
        img_height (int): 原始图像高度。

    返回:
        numpy.ndarray: 绘制了检测结果的输入图像。
    """

    # 转置和压缩输出以匹配预期的形状
    outputs = np.transpose(np.squeeze(output[0]))
    # 获取输出数组的行数
    rows = outputs.shape[0]
    # 用于存储检测的边界框、得分和类别ID的列表
    boxes = []
    scores = []
    class_ids = []
    # 计算边界框坐标的缩放因子
    x_factor = img_width / input_width
    y_factor = img_height / input_height
    # 遍历输出数组的每一行
    for i in range(rows):
        # 从当前行提取类别得分
        classes_scores = outputs[i][4:]
        # 找到类别得分中的最大得分
        max_score = np.amax(classes_scores)
        # 如果最大得分高于置信度阈值
        if max_score >= confidence_thres:
            # 获取得分最高的类别ID
            class_id = np.argmax(classes_scores)
            # 从当前行提取边界框坐标
            x, y, w, h = outputs[i][0], outputs[i][1], outputs[i][2], outputs[i][3]
            # 计算边界框的缩放坐标
            left = int((x - w / 2) * x_factor)
            top = int((y - h / 2) * y_factor)
            width = int(w * x_factor)
            height = int(h * y_factor)
            # 将类别ID、得分和框坐标添加到各自的列表中
            class_ids.append(class_id)
            scores.append(max_score)
            boxes.append([left, top, width, height])
    # 应用非最大抑制过滤重叠的边界框
    indices = custom_NMSBoxes(boxes, scores, confidence_thres, iou_thres)
    # 遍历非最大抑制后的选定索引
    for i in indices:
        # 根据索引获取框、得分和类别ID
        box = boxes[i]
        score = scores[i]
        class_id = class_ids[i]
        # 在输入图像上绘制检测结果
        draw_detections(input_image, box, score, class_id)
    # 返回修改后的输入图像
    return input_image

def init_detect_model(onnx_model):
    # 使用ONNX模型文件创建一个推理会话，并指定执行提供者
    session = ort.InferenceSession(onnx_model, providers=providers)
    # 获取模型的输入信息
    model_inputs = session.get_inputs()
    # 获取输入的形状，用于后续使用
    input_shape = model_inputs[0].shape
    # 从输入形状中提取输入宽度
    input_width = input_shape[2]
    # 从输入形状中提取输入高度
    input_height = input_shape[3]
    # 返回会话、模型输入信息、输入宽度和输入高度
    return session, model_inputs, input_width, input_height

def detect_object(image, session, model_inputs, input_width, input_height):
    # 如果输入的图像是PIL图像对象，将其转换为NumPy数组
    if isinstance(image, Image.Image):
        result_image = np.array(image)
    else:
        # 否则，直接使用输入的图像（假定已经是NumPy数组）
        result_image = image
    # 预处理图像数据，调整图像大小并可能进行归一化等操作
    img_data, img_height, img_width = preprocess(result_image, input_width, input_height)
    # 使用预处理后的图像数据进行推理
    outputs = session.run(None, {model_inputs[0].name: img_data})
    # 对推理结果进行后处理，例如解码检测框，过滤低置信度的检测等
    output_image = postprocess(result_image, outputs, input_width, input_height, img_width, img_height)
    # 返回处理后的图像
    return output_image

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


def main(args: argparse.Namespace) -> None:

    #视频地址和流媒体服务器地址
    onnx_model = args.onnx
    video_url = args.video_url
    stream_server_url = args.stream_server_url


    # 初始化检测模型，加载模型并获取模型输入节点信息和输入图像的宽度、高度
    session, model_inputs, input_width, input_height = init_detect_model(onnx_model)
   
    # 判断是否使用GPU推理
    if 'CUDAExecutionProvider' in session.get_providers():
        print('模型正在使用GPU进行推理...')
    else:
        print('模型没有使用GPU进行推理...')

    # 三种模式 1为图片预测，并显示结果图片；2为摄像头检测，并实时显示FPS； 3为视频检测，并保存结果视频
    mode = 3
    if mode == 1:
        # 读取图像文件
        image_data = cv2.imread("street.jpg")
        # 使用检测模型对读入的图像进行对象检测
        result_image = detect_object(image_data, session, model_inputs, input_width, input_height)
        # 将检测后的图像保存到文件
        cv2.imwrite("output_image.jpg", result_image)
        # 在窗口中显示检测后的图像
        cv2.imshow('Output', result_image)
        # 等待用户按键，然后关闭显示窗口
        cv2.waitKey(0)
    elif mode == 2:
        # 打开摄像头
        cap = cv2.VideoCapture()  # 0表示默认摄像头，如果有多个摄像头可以尝试使用1、2等
        # 检查摄像头是否成功打开
        if not cap.isOpened():
            print("Error: Could not open camera.")
            exit()
        # 初始化帧数计数器和起始时间
        frame_count = 0
        start_time = time.time()
        # 循环读取摄像头视频流
        while True:
            # 读取一帧
            ret, frame = cap.read()
            # 检查帧是否成功读取
            if not ret:
                print("Error: Could not read frame.")
                break
            # 使用检测模型对读入的帧进行对象检测
            output_image = detect_object(frame, session, model_inputs, input_width, input_height)
            # 计算帧速率
            frame_count += 1
            end_time = time.time()
            elapsed_time = end_time - start_time
            fps = frame_count / elapsed_time
            print(f"FPS: {fps:.2f}")
            # 将FPS绘制在图像上
            cv2.putText(output_image, f"FPS: {fps:.2f}", (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 255, 255), 2, cv2.LINE_AA)
            # 在窗口中显示当前帧
            cv2.imshow("Video", output_image)
            # 按下 'q' 键退出循环
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break
        # 释放摄像头资源
        cap.release()
        # 关闭窗口
        cv2.destroyAllWindows()
    elif mode == 3:
        # 打开视频文件
        cap = cv2.VideoCapture(video_url)

        # 视频属性
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
        model_prefixx = args.onnx.split(".")[0]
        bucket_name = 'algcenter-' + model_prefixx.replace('_', '-')

        # (9).开启新的线程执行定时任务(每隔60秒更新一次push_flag为1，以便每隔60秒保存识别图片一次)
        pool.submit(exec_schedule)

        # (10).分类id及对应的最高评分
        cls_score = {}


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

            # 推送原始视频到流媒体服务器
            pipe_ori.stdin.write(frame.tobytes())

            # 计算检测结果并返回画框后的图像
            output_image = detect_object(frame, session, model_inputs, input_width, input_height)
            # 推送实时检测视频到流媒体服务器
            pipe_dec.stdin.write(output_image.tobytes())




        # 释放资源
        cap.release()
        cv2.destroyAllWindows()
    else:
        print("输入错误，请检查mode的赋值")

def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser()
    parser.add_argument('--onnx', type=str, required=False, default='yolov8n.onnx', help='Onnx file')
    parser.add_argument('--video_url', type=str, required=False, default='https://qbj.jszhcs.cn:9589/dj-cloud-bucket/wayline/e864c152-240e-4250-ac2e-8b8f2252997e/DJI_202403131744_006_e864c152-240e-4250-ac2e-8b8f2252997e/DJI_20240313174739_0002_Z.mp4', help='Video url or file')
    parser.add_argument('--stream_server_url', type=str, required=False, default='rtmp://192.168.2.241:11935/stream/live/dec_onnxde01?sign=41db35390ddad33f83944f44b8b75ded', help='stream server url')
    parser.add_argument('--device',type=str,default='cuda:0',help='Onnx infer device')
    parser.add_argument('--skip_frame',type=int,default='1',help='Skip Frame')
    parser.add_argument('--push_frq',type=int,default='40',help='Push Frequency')
    args = parser.parse_args()
    return args

if __name__ == '__main__':
    args = parse_args()
    main(args)

