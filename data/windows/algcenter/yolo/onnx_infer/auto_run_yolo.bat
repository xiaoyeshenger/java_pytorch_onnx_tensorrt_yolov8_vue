@echo off

:: 设置字符编码为UTF-8,避免中文乱码
chcp 65001
cls

python yolov8_detect_onnx.py --onnx yolov8n.onnx --video_url car3.mp4 --stream_server_url rtmp://192.168.2.241:11935/stream/live/dec_bnhy5jmG?sign=41db35390ddad33f83944f44b8b75ded --skip_frame 1 --push_frq 60
