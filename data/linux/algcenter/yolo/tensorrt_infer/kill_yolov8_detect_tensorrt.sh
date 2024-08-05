#!/bin/bash

# 执行下面命令运行该脚本
# sh kill_yolov8_detect_tensorrt.sh python yolov8_tensorrt_detect.py yolov8n.engine https://qbj.jszhcs.cn:9589/dj-cloud-bucket/wayline/e864c152-240e-4250-ac2e-8b8f2252997e/DJI_202403131744_006_e864c152-240e-4250-ac2e-8b8f2252997e/DJI_20240313174739_0002_Z.mp4 rtmp://192.168.2.241:11935/stream/live/dec_bnhy5jmG?sign=41db35390ddad33f83944f44b8b75ded

# 判断参数个数是否等于2
if [ "$#" -ne 7 ]; then
    echo "传入的参数数量不对，必须为7个参数"
    exit 1
fi

# 将5个参数拼接成关键字
keyword="$1 $2 --engine $3 --video_url $4 --stream_server_url $5"
echo $keyword

# 通过关键字查询到进程号pid
pid=$(ps aux | grep "$keyword" | grep -v "grep" | awk '{print $2}')
#pid=$(ps aux | grep "python yolov8_detect_tensorrt.py --engine yolov8n.engine --video_url https://qbj.jszhcs.cn:9589/dj-cloud-bucket/wayline/e864c152-240e-4250-ac2e-8b8f2252997e/DJI_202403131744_006_e864c152-240e-4250-ac2e-8b8f2252997e/DJI_20240313174739_0002_Z.mp4 --stream_server_url rtmp://192.168.2.241:11935/stream/live/dec_bnhy5jmG?sign=41db35390ddad33f83944f44b8b75ded" | grep -v "grep" | awk '{print $2}')
echo $pid

# pid不为空则以后台运行的方式执行kill -9 杀死进程，并将日志保存到logfile
if [ -n "$pid" ]; then
    logfile="logs/log_kill_yolov8_$3.log"
    nohup > $logfile 2>&1 kill -9 $pid & 
fi

