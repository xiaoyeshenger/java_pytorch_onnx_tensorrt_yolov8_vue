import cv2
import time
from datetime import datetime
from io import BytesIO
import concurrent.futures
import threading
import asyncio
import uuid
import schedule
from .rocketmq_util import send_msg
from .minio_util import Bucket


pool = concurrent.futures.ThreadPoolExecutor(max_workers=2)

# 只推送置信度大于0.5的告警图片
base_score = 0.5

# 推送flag为1才能执行推送
push_flag = 0;


def exec_schedule():
    time.sleep(3)
    print("开始新线程执行exec_schedule-------------------->")
    # 时间间隔(60秒只推送1次)
    def reset_push_flag():
        print("进来重置push--------------------")
        global push_flag
        push_flag = 1
    schedule.every(60).seconds.do(reset_push_flag)
    while True:
        schedule.run_pending()
        time.sleep(60)

def generate_short_uuid():
    # 生成标准的UUID
    long_uuid = uuid.uuid4().hex
    # 返回UUID的前四个字符
    return long_uuid[:4]

def push_minio_rocketmq(frame, file_name, bucket_name, group_name, topic, msg_body):
    # 1.保存到minio
    image_bytes = cv2.imencode('.jpg', frame)[1].tobytes()
    binary_io = BytesIO(image_bytes)
    Bucket(binary_io, file_name, bucket_name).createBucketAndUploadFlieStreamAnd()

    # 2.发送消息到rocketmq
    send_msg('192.168.2.6:9876', group_name, topic, msg_body)



if __name__ == '__main__':
    print("main开始")
    pool.submit(exec_schedule)
    print("main结束")
    video_path = "../car3.mp4"
    cap = cv2.VideoCapture(video_path)
    while cap.isOpened():
        status, frame = cap.read()
        if not status:
            break

        #cv2.imshow("V8", frame)

        #保存图片到minio并推送消息到rocketmq
        formatted_datetime = datetime.now().strftime("%m-%d-%Y %H:%M:%S")
        start_time = time.time()

        #单线程同步执行
        #push_minio_rocketmq(frame, 'sjajkfkjs','1','yolov8_sl_fh')

        cur_socre = 0.8
        #线程池异步执行
        if cur_socre > base_score and push_flag == 1:
            print('进入pushing..............',push_flag)
            msg_body = {"task_no": 'ddd', "model_name": '123', "img_url": '123',"bucket_name": 'algcenter-yolov8-sl-fh'}
            pool.submit(push_minio_rocketmq, frame, 'sjajkfkjs','1','yolov8_sl_fh',msg_body)
            push_flag = 0

        #异步执行
        #asyncio.run(push_minio_rocketmq(frame, 'sduguh', '1', 'yolov8_sl_fh'))

        #print("per frame push cost time:",str((time.time() - start_time)*1000),"ms")
        print("当前 push_flag---------->:",str(push_flag))

        time.sleep(5)

        # 设置延迟以减慢视频播放速度
        #if cv2.waitKey(30) & 0xFF == ord('q'):
            #break
    cap.release()
    cv2.destroyAllWindows()