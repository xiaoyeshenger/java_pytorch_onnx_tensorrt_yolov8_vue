#!/bin/bash

#python yolo.py yolov8n.onnx car3.mp4 rtmp://192.168.2.241:11935/stream/live/dec_999?sign=41db35390ddad33f83944f44b8b75ded

# 获取到模型名称 视频地址 流媒体地址
model_name=$1
video_url=$2
stream_server_url=$3
skip_frame=$4
push_frq=$5
#echo "第1个参数为：$model_path,第2个参数为：$input_video_path,第3个参数为：$output_video_path, 第4个参数为: $skip_frame, 第5个参数为: $push_frq"

#如果跳帧为空，则赋值为1
if [ -z "$4" ]; then
    skip_frame=1
fi

#如果推送频率为空，则赋值为60
if [ -z "$4" ]; then
    push_frq=60
fi

#current_datetime=$(date +'%Y-%m-%d %H:%M:%S')
#logfile="$model_path_$current_datetime.log"
logfile="logs/run_yolo_$1.log"

# 以后台运行的方式执行python脚本，并将日志保存到logfile
nohup > $logfile 2>&1 python yolov8_detect_onnx.py  --onnx $model_name --video_url $video_url --stream_server_url $stream_server_url --skip_frame $skip_frame --push_frq $push_frq &

