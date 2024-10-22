@echo off

:: 设置字符编码为UTF-8,避免中文乱码
chcp 65001
cls

:: 初始化参数计数器
set /a param_count=0
 
:: 遍历所有命令行参数
:: echo %%param_count%%: %%p
for %%p in (%*) do (
    set /a param_count+=1
)
 
:: 判断参数个数
if %param_count% lss 5 (
    echo "参数个数不对,需要5个参数"
    exit /b 1
)

:: 由于流媒体服务器 sign=41db35390ddad33f83944f44b8b75ded 包含=号，bat会识别=后的字符串为第4个参数%4，所以完整地址是%3和%4中间加上等号进行拼接
set streamServerUrl=%3=%4
:: echo %streamServerUrl%

:: 执行脚本
:: start /b conda run -n yolo_env cmd /c yolov8_detect_tensorrt.bat yolov8n.engine car3.mp4 rtmp://192.168.2.241:11935/stream/live/999?sign=41db35390ddad33f83944f44b8b75ded 1 60
:: python yolov8_detect_tensorrt.py %1 %2 %streamServerUrl% > run_yolo_log.txt 2>&1
:: start /b 即开启新线程执行命令(b=background)
start /b python yolov8_detect_tensorrt.py %1 %2 %streamServerUrl% %5 %6 > run_yolo_log.txt 2>&1
