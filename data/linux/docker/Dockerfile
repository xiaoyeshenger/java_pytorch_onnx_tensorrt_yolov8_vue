# 基于官方的Ubuntu镜像
FROM ubuntu:20.04

# 安装JDK
RUN apt-get update
RUN apt-get install -y openjdk-11-jdk
 
# 安装Python
RUN apt-get install -y python3.8
RUN apt-get install -y python3-distutils
RUN apt-get install -y python3-pip
RUN ln -s /usr/bin/python3 /usr/bin/python

# 安装ffmpeg
RUN apt-get install -y ffmpeg

# 安装opencv(源码编译安装)
RUN apt-get install -y cmake
RUN apt-get install -y build-essential 
RUN apt-get install -y libgtk2.0-dev libavcodec-dev libavformat-dev libjpeg-dev libswscale-dev libtiff5-dev
RUN apt-get install -y libcanberra-gtk-module
RUN apt-get install -y pkg-config
RUN apt-get install -y zip unzip
COPY ./opencv/opencv-4.7.0.zip /gsis_ai/opencv/opencv-4.7.0.zip
RUN unzip -d /gsis_ai/opencv/opencv-4.7.0 /gsis_ai/opencv/opencv-4.7.0.zip
RUN cd /gsis_ai/opencv/opencv-4.7.0/opencv-4.7.0
RUN mkdir build && cd build
RUN cmake -D CMAKE_BUILD_TYPE=Release -D CMAKE_INSTALL_PREFIX=/usr/local ..
RUN make -j$(nproc)
RUN make install
RUN opencv_version
RUN cd /

# 安装expect以便自动安装cuda11.8
RUN apt-get install -y expect

# 安装cuda11.8(#号注释的命令行,进入容器后再手动执行,下面等同)
COPY ./nvidia/cuda_11.8.0_520.61.05_linux.run /gsis_ai/nvidia/cuda_11.8.0_520.61.05_linux.run
#RUN /gsis_ai/nvidia/cuda_11.8.0_520.61.05_linux.run
#RUN sed -i '$a\export PATH=$PATH:/usr/local/cuda-11.8/bin' ~/.bashrc
#RUN sed -i '$a\export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/cuda-11.8/lib64' ~/.bashrc
#RUN source ~/.bashrc
#RUN nvcc -V

# 安装cudnn8.6.0
COPY ./nvidia/cudnn-linux-x86_64-8.6.0.163_cuda11-archive.tar.xz /gsis_ai/nvidia/cudnn-linux-x86_64-8.6.0.163_cuda11-archive.tar.xz 
RUN tar -xvf /gsis_ai/nvidia/cudnn-linux-x86_64-8.6.0.163_cuda11-archive.tar.xz -C /gsis_ai/nvidia/
RUN cp /gsis_ai/nvidia/cudnn-linux-x86_64-8.6.0.163_cuda11-archive/lib/* /usr/local/cuda/lib64
RUN cp /gsis_ai/nvidia/cudnn-linux-x86_64-8.6.0.163_cuda11-archive/include/* /usr/local/cuda/include
RUN cat /usr/local/cuda/include/cudnn_version.h | grep CUDNN_MAJOR -A 2

# 安装TensorRT8.5.1.7
COPY ./nvidia/TensorRT-8.5.1.7.Linux.x86_64-gnu.cuda-11.8.cudnn8.6.tar.gz /gsis_ai/nvidia/TensorRT-8.5.1.7.Linux.x86_64-gnu.cuda-11.8.cudnn8.6.tar.gz
RUN tar -xvf /gsis_ai/nvidia/TensorRT-8.5.1.7.Linux.x86_64-gnu.cuda-11.8.cudnn8.6.tar.gz -C /gsis_ai/nvidia/
RUN sed -i '$a\export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/gsis_ai/nvidia/TensorRT-8.5.1.7/lib' ~/.bashrc 
RUN source ~/.bashrc
RUN python3 -m pip install /gsis_ai/nvidia/TensorRT-8.5.1.7/python/tensorrt-8.5.1.7-cp38-none-linux_x86_64.whl -i https://pypi.tuna.tsinghua.edu.cn/simple/
RUN python3 -m pip install /gsis_ai/nvidia/TensorRT-8.5.1.7/uff/uff-0.6.9-py2.py3-none-any.whl -i https://pypi.tuna.tsinghua.edu.cn/simple/
RUN python3 -m pip install /gsis_ai/nvidia/TensorRT-8.5.1.7/graphsurgeon/graphsurgeon-0.4.6-py2.py3-none-any.whl -i https://pypi.tuna.tsinghua.edu.cn/simple/
RUN python3 -m pip install /gsis_ai/nvidia/TensorRT-8.5.1.7/onnx_graphsurgeon/onnx_graphsurgeon-0.3.12-py2.py3-none-any.whl -i https://pypi.tuna.tsinghua.edu.cn/simple/
#RUN python -c "import tensorrt; print(tensorrt.__version__)"

# 安装onnx推理依赖(此处安装的是GPU版本onnxruntime-gpu==1.16.1)
RUN python3 -m pip install --upgrade pip
RUN python3 -m pip install onnxruntime-gpu==1.16.1 -i https://pypi.tuna.tsinghua.edu.cn/simple/
RUN python3 -m pip install opencv-python==4.9.0.80 -i https://pypi.tuna.tsinghua.edu.cn/simple/
RUN python3 -m pip install numpy==1.24.1 -i https://pypi.tuna.tsinghua.edu.cn/simple/
RUN python3 -m pip install Pillow==9.4.0 -i https://pypi.tuna.tsinghua.edu.cn/simple/
RUN python -c "import onnxruntime; print(onnxruntime.__version__); print(onnxruntime.get_device())"

# 安装pytorch
RUN pip install torch==2.3.0 torchvision==0.18.0 torchaudio==2.3.0 --index-url https://download.pytorch.org/whl/cu118
RUN python -c "import torch; print('CUDA version:', torch.version.cuda); print('CUDA is_available ?:', torch.cuda.is_available()); print('gpu count:',torch.cuda.device_count())"

# 安装yolov8
pip install ultralytics==8.2.48 -i https://pypi.tuna.tsinghua.edu.cn/simple/

# 清理安装包及软件包列表
RUN apt-get clean && rm -rf /var/lib/apt/lists/

# 设置JAVA环境变量
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64/
ENV PATH $JAVA_HOME/bin:$PATH
 
# 设置工作目录
WORKDIR /data/app/
