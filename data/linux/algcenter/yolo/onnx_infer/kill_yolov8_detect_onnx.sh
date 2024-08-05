#!/bin/bash

# 执行下面命令运行该脚本
#sh kill_yolov8_detect_onnx.sh "python yolov8_detect_onnx.py yolov8n.onnx car3.mp4 rtmp://192.168.2.241:11935/stream/live/car3?sign=41db35390ddad33f83944f44b8b75ded"

# 判断参数个数是否等于2
if [ "$#" -ne 7 ]; then
    echo "传入的参数数量不对，必须为7个参数"
    exit 1
fi

# 将5个参数拼接成关键字
keyword="$1 $2 --onnx $3 --video_url $4 --stream_server_url $5"
#echo $keyword

# 通过关键字查询到进程号pid
#pid=$(ps aux | grep "yolo.py" | grep -v "grep" | awk '{print $2}')
pid=$(ps aux | grep "$keyword" | grep -v "grep" | awk '{print $2}')
#pid=$(ps aux | grep "python yolov8_detect_onnx.py yolov8n.onnx https://qbj.jszhcs.cn:9589/dj-cloud-bucket/wayline/47e5f853-115e-466f-a753-26fd8d18cab2/DJI_202403060928_006_47e5f853-115e-466f-a753-26fd8d18cab2/DJI_20240306093113_0002_Z.mp4 rtmp://192.168.2.241:11935/stream/live/bnhy5jmG?sign=41db35390ddad33f83944f44b8b75ded" | grep -v "grep" | awk '{print $2}')
echo $pid

# pid不为空则以后台运行的方式执行kill -9 杀死进程，并将日志保存到logfile
if [ -n "$pid" ]; then
    logfile="logs/kill_yolo_$3.log"
    nohup > $logfile 2>&1 kill -9 $pid & 
fi

