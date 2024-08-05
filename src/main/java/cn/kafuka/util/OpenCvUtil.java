/*
package cn.kafuka.util;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import cn.kafuka.algorithm.*;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_java;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.FloatBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class OpenCvUtil {


    //private static final ExecutorService executorService = SpringUtil.getBean("executorService", ExecutorService.class);

    public static volatile Map<String, Object> FFmpegMap = new ConcurrentHashMap<>();

    static {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Loader.load(opencv_java.class);
        }
        if (OS.contains("linux")) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Loader.load(opencv_java.class);
        }
    }


    */
/**
     * (1).基于javacv的FFmpegFrameGrabber获取视频的帧数据Frame,然后对视频进行目标检测并实时检测视频推送到流媒体服务器/保存到本地
     *   deviceSerial:设备序列号(代表每一路视频)
     *   videoURL:视频地址
     *   streamServerUrl:流媒体服务器推流地址("rtmp://222.210.127.152:1935/stream/live/" + deviceSerial + "?sign=41db35390ddad33f83944f44b8b75ded";)
     *                   或者本地文件地址(E:/ideaGitPro/algorithmCenter/src/main/resources/video/test.mp4)
     *   modelBytes:运算模型的字节数组
     *   labels:标签分别名称字符串数组
     *   detectSkip:跳帧大小(每个多少帧检测运算一次)
     *   confThreshold:置信度
     *   nmsThreshold:非极大值抑制大小
     *//*

    public static void javaCvPushStreamAndDectect(String deviceSerial,String videoURL, String streamServerUrl,byte[] modelBytes,String[] labels,Integer detectSkip,float confThreshold,float nmsThreshold) throws OrtException {

        //1.加载模型
        //(1).创建Ort(OnnxRuntime)环境
        OrtEnvironment environment = OrtEnvironment.getEnvironment();

        //(2).创建OrtSession选项，比如设置通过gpu计算,如果使用gpu,增加sessionOptions.addCUDA(0);需要本机安装过cuda，并修改pom.xml,实际项目中，视频识别必须开启GPU，并且要防止队列堆积
        OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();

        //(3).通过OrtEnvironment创建OrtSession
        OrtSession session = environment.createSession(modelBytes, sessionOptions);

        //(4).打印模型的输入信息(也可在netron工具中可视化查看)
        session.getInputInfo().keySet().forEach(x -> {
            try {
                System.out.println("input name = " + x);
                System.out.println("input value = " + session.getInputInfo().get(x).getInfo().toString());
            } catch (OrtException e) {
                throw new RuntimeException(e);
            }
        });


        //2.检查是否重复推流
        if(FFmpegMap.containsKey(deviceSerial+"_grabber") || FFmpegMap.containsKey(deviceSerial+"_recorder")){
            return;
        }

        //3.初始化视频捕获器grabber
        FFmpegFrameGrabber grabber = initFFmpegFrameGrabber(videoURL);

        //4.初始化视频记录器recorder
        FFmpegFrameRecorder recorder = initFFmpegFrameRecorder(streamServerUrl,25,grabber.getImageWidth(), grabber.getImageHeight());

        //5.将该视频的grabber和recorder状态记录到map中,以便可以手动关闭,后续将FFmpegMap的play_stop设置为true时即可关闭拉流和推流
        FFmpegMap.put(deviceSerial+"_grabber",grabber);
        FFmpegMap.put(deviceSerial+"_recorder",recorder);
        FFmpegMap.put(deviceSerial+"_play_stop",false);


        //6.视频检测跳帧设置
        //(1).跳帧大小
        int detect_skip = detectSkip;
        //(2).跳帧计数
        int detect_skip_index = 1;

        //7.执行检测运算后视频的帧数据是否推流到流媒体或保存到本地磁盘
        Boolean pushVideoStream = true;

        //8.可视化窗口
        */
/*CanvasFrame canvas = new CanvasFrame("Yolo_Camera");
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);*//*


        //8.对视频进行检测运算
        try {
            while (true) {
                //(1).获取到到视频当前帧数据
                Frame frame  =  grabber.grabImage();
                if (frame == null) {
                    break;
                }
                if((Boolean) FFmpegMap.get(deviceSerial + "_play_stop")){
                    break;
                }

                //(2).转为mat
                Mat img = convertToMat(frame);

                //(3).执行运算
                if ((detect_skip_index % detect_skip == 0)){
                    //--1.开启新的线程运行推理计算
                    //calculateImg(mat,environment,session,labels,confThreshold,nmsThreshold,pushVideoStream,recorder);
                    */
/*executorService.execute(()->{
                        calculateImg(img,environment,session,labels,confThreshold,nmsThreshold,pushVideoStream,recorder);
                    });*//*


                    //--2.将跳帧计数重置为1
                    detect_skip_index = 1;
                }else{
                    if(pushVideoStream){
                        recorder.record(frame);
                    }
                    detect_skip_index = detect_skip_index + 1;
                }


                //recorder.record(frame);
                //canvas.showImage(frame);

                */
/*try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*//*

            }

            //(4).视频帧为空则停止推流
            FFmpegMap.remove(deviceSerial+"_grabber");
            FFmpegMap.remove(deviceSerial+"_recorder");
            recorder.stop();
            recorder.release();
            grabber.stop();
            grabber.release();

        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        } finally {
            if (recorder != null) {
                try {
                    FFmpegMap.remove(deviceSerial+"_recorder");
                    recorder.stop();
                    recorder.release();
                } catch (FrameRecorder.Exception e) {
                    e.printStackTrace();
                }
            }
            if (grabber != null) {
                try {
                    FFmpegMap.remove(deviceSerial+"_grabber");
                    grabber.stop();
                    grabber.release();
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    */
/**
     * (2).基于opencv的VideoCapture获取视频的帧数据Mat,然后对视频进行目标检测并实时检测视频推送到流媒体服务器/保存到本地
     *   deviceSerial:设备序列号(代表每一路视频)
     *   videoURL:视频地址
     *   streamServerUrl:流媒体服务器推流地址("rtmp://222.210.127.152:1935/stream/live/" + deviceSerial + "?sign=41db35390ddad33f83944f44b8b75ded";)
     *                   或者本地文件地址(E:/ideaGitPro/algorithmCenter/src/main/resources/video/test.mp4)
     *   modelBytes:运算模型的字节数组
     *   labels:标签分别名称字符串数组
     *   detectSkip:跳帧大小(每个多少帧检测运算一次)
     *   confThreshold:置信度
     *   nmsThreshold:非极大值抑制大小
     *//*

    public static void opencvCvPushStreamAndDectect(String deviceSerial,String videoURL, String streamServerUrl,byte[] modelBytes,String[] labels,Integer detectSkip,float confThreshold,float nmsThreshold) throws OrtException {

        //1.加载模型
        //(1).创建Ort(OnnxRuntime)环境
        OrtEnvironment environment = OrtEnvironment.getEnvironment();

        //(2).创建OrtSession选项，比如设置通过gpu计算,如果使用gpu,增加sessionOptions.addCUDA(0);需要本机安装过cuda，并修改pom.xml,实际项目中，视频识别必须开启GPU，并且要防止队列堆积
        OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();

        //(3).通过OrtEnvironment创建OrtSession
        OrtSession session = environment.createSession(modelBytes, sessionOptions);

        //(4).打印模型的输入信息(也可在netron工具中可视化查看)
        session.getInputInfo().keySet().forEach(x -> {
            try {
                System.out.println("input name = " + x);
                System.out.println("input value = " + session.getInputInfo().get(x).getInfo().toString());
            } catch (OrtException e) {
                throw new RuntimeException(e);
            }
        });


        //2.打开video,当前主线程实时拉流(可以把识别后的视频在通过rtmp转发到其他流媒体服务器，就可以远程预览视频后视频，需要使用ffmpeg将连续图片合成flv 等等，很简单。)
        VideoCapture videoCapture = new VideoCapture();
        videoCapture.open(videoURL);
        if (!videoCapture.isOpened()) {
            throw new IllegalArgumentException("视频无法打开("+videoURL+")");
        }
        Integer width = (int)videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH);
        Integer height = (int)videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT);
        int fps = (int) videoCapture.get(Videoio.CAP_PROP_FPS);
        int playDelay = Math.round(1000/fps);
        double frameIndex = videoCapture.get(Videoio.CAP_PROP_POS_FRAMES);
        double frameCount = videoCapture.get(Videoio.CAP_PROP_FRAME_COUNT);
        double timeLength = frameCount / fps;
        System.out.println("视频基本信息，宽度:"+width+",高度:"+height+",帧率:"+fps+",视频当前帧索引:"+frameIndex+",视频总帧数:"+frameCount+",视频总时长:"+timeLength);



        //3.检查是否重复推流
        if(FFmpegMap.containsKey(deviceSerial+"_recorder")){
            return;
        }

        //4.初始化视频记录器recorder
        FFmpegFrameRecorder recorder = initFFmpegFrameRecorder(streamServerUrl,fps,width, height);

        //5.将该视频的grabber和recorder状态记录到map中,以便可以手动关闭,后续将FFmpegMap的play_stop设置为true时即可关闭拉流和推流
        FFmpegMap.put(deviceSerial+"_recorder",recorder);
        FFmpegMap.put(deviceSerial+"_play_stop",false);


        //6.视频检测跳帧设置
        //(1).跳帧大小
        int detect_skip = detectSkip;
        //(2).跳帧计数
        int detect_skip_index = 1;

        //7.执行检测运算后视频的帧数据是否推流到流媒体或保存到本地磁盘
        Boolean pushVideoStream = true;


        */
/*Size frameSize = new Size(width, height);
        VideoWriter videoWriter = new VideoWriter();
        videoWriter.open(streamServerUrl, VideoWriter.fourcc('M','J','P','G'), fps, frameSize, true);*//*


        //8.对视频进行检测运算
        try {
            Mat img = new Mat();
            Long startTime = System.currentTimeMillis();
            while (videoCapture.read(img)) {
                //System.out.println("当前时间:"+DateUtil.getCurTimeStr());

                //(1).执行推理(命中跳帧计数或最新推理结果为空则执行推理，反之跳帧计数+1)
                if ((detect_skip_index % detect_skip == 0)){
                    //--1.开启新的线程运行推理计算
                    img = calculateImg(img,environment,session,labels,confThreshold,nmsThreshold,pushVideoStream,recorder);
                    */
/*executorService.execute(()->{
                        calculateImg(img,environment,session,labels,confThreshold,nmsThreshold,pushVideoStream,recorder);
                    });*//*


                    //--2.将跳帧计数重置为1
                    detect_skip_index = 1;
                }else{
                    */
/*if(pushVideoStream){
                        pushStreamToServer(recorder,img);
                    }*//*

                    detect_skip_index = detect_skip_index + 1;
                }

                //向视频帧写入本地磁盘，形成mp4视频文件
                //videoWriter.write(img);

                //recorder.record(convertToFrame(img));

                //(2).如果是pc机上有显示器，可以弹出实时计算画面(在服务器部署时由于服务器没有桌面，所以无法弹出画面预览，主要注释一下该代码)
                System.setProperty("java.awt.headless", "false");
                HighGui.imshow("result", img);

                //(3).多次按任意按键关闭弹窗画面，结束程序(显示完一帧图像后程序等待”delay”ms再显示下一帧视频)
                */
/*if(HighGui.waitKey(playDelay) != -1){
                break;
                }*//*


                if(HighGui.waitKey(playDelay) == 27) {
                    System.out.println("用户按下'ESC'键，结束推流");
                    break;
                }

            }

            //(4).视频帧为空则停止推流
            FFmpegMap.remove(deviceSerial+"_recorder");
            recorder.stop();
            recorder.release();

        }  catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        } finally {
            if (recorder != null) {
                try {
                    FFmpegMap.remove(deviceSerial+"_recorder");
                    recorder.stop();
                    recorder.release();
                } catch (FrameRecorder.Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void pushStreamToServer(FFmpegFrameRecorder fFmpegFrameRecorder,Mat img){
        //将Mat对象转换为Frame对象并推送视频到流媒体服务器
        Frame frame = convertToFrame(img);
        try {
            fFmpegFrameRecorder.record(frame);
        } catch (FFmpegFrameRecorder.Exception e) {
            throw new RuntimeException(e);
        }
    }


    */
/**
     * (3).将视频流地址推送(转发)到流媒体服务器
     *//*

    public static void pushStream(String deviceSerial,String videoURL, String streamServerUrl) {
        //1.检查是否重复推流
        if(FFmpegMap.containsKey(deviceSerial+"_grabber")){
            return;
        }

        if(FFmpegMap.containsKey(deviceSerial+"_recorder")){
            return;
        }

        //2.初始化视频捕获器grabber
        FFmpegFrameGrabber grabber = initFFmpegFrameGrabber(videoURL);

        //3.初始化视频记录器recorder
        FFmpegFrameRecorder recorder = initFFmpegFrameRecorder(streamServerUrl,25,grabber.getImageWidth(), grabber.getImageHeight());


        //4.推送视频流
        try {
            //(1).将该设备的grabber和recorder状态记录到map中,以便可以手动关闭
            FFmpegMap.put(deviceSerial+"_grabber",grabber);
            FFmpegMap.put(deviceSerial+"_recorder",recorder);
            FFmpegMap.put(deviceSerial+"_play_stop",false);

            //(2).循环读取视频帧并推流
            while (true) {
                //--1.获取到到视频当前帧数据
                Frame frame  =  grabber.grabImage();
                if (frame == null) {
                    break;
                }
                if((Boolean) FFmpegMap.get(deviceSerial + "_play_stop")){
                    break;
                }

                //--2.通过recorder写入到流媒体服务器或本地磁盘
                recorder.record(frame);
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

            //(3).视频帧为空则停止推流
            FFmpegMap.remove(deviceSerial+"_grabber");
            FFmpegMap.remove(deviceSerial+"_recorder");
            recorder.stop();
            recorder.release();
            grabber.stop();
            grabber.release();

        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        } finally {
            if (recorder != null) {
                try {
                    FFmpegMap.remove(deviceSerial+"_recorder");
                    recorder.stop();
                    recorder.release();
                } catch (FrameRecorder.Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != grabber) {
                try {
                    FFmpegMap.remove(deviceSerial+"_grabber");
                    grabber.stop();
                    grabber.release();
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }




    */
/**
     * (3).初始化视频捕获器FFmpegFrameRecorder
     *   videoUrl:视频流地址/文件地址
     *//*

    private static FFmpegFrameGrabber initFFmpegFrameGrabber(String videoUrl) {

        //1.创建FFmpegFrameGrabber对象
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoUrl);

        //2.初始化参数
        //使用tcp协议
        grabber.setOption("rtsp_transport", "tcp");
        //设置缓存大小，提高画质、减少卡顿花屏
        grabber.setOption("buffer_size", "1024000");
        //设置视频比特率为2Mbps。可以根据实际情况进行调整。，这里是2000kb/s(2m)
        grabber.setVideoBitrate(2000 * 1000);

        try {
            grabber.start();
        } catch (FFmpegFrameGrabber.Exception e) {
            throw new RuntimeException(e);
        }

        //3.启动grabber
        return grabber;
    }

    */
/**
     * (4).初始化视频记录器FFmpegFrameRecorder
     *   streamServerUrl:流媒体服务器推流地址
     *   fps:帧率
     *   width:视频宽度
     *   height:视频高度
     *//*

    private static FFmpegFrameRecorder initFFmpegFrameRecorder(String streamServerUrl,Integer fps,Integer width,Integer height) {

        //1.创建FFmpegFrameRecorder对象
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(streamServerUrl, width, height);

        //2.初始化参数
        //(1).视频参数
        //设置使用TCP传输协议，可以提高稳定性和速度
        recorder.setOption("rtsp_transport", "tcp");
        //intel核显编码仅支持nv12和p010le
        recorder.setPixelFormat(org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_NV12);
        //硬件编码h264
        recorder.setVideoCodecName("h264_qsv");
        //设置视频比特率为2Mbps。可以根据实际情况进行调整。，这里是2000kb/s(2m)
        recorder.setVideoBitrate(2000 * 1000);
        //禁用B帧，降低延迟
        recorder.setMaxBFrames(0);
        //封装格式
        recorder.setFormat("flv");
        //关键帧间距
        recorder.setGopSize(fps * 3);
        //帧率
        recorder.setFrameRate(fps);
        recorder.setInterleaved(true);
        //设置预设，zerolatency表示零延迟，适用于实时视频会议(ultrafast(对cpu消耗最低),superfast,veryfast,faster,fast,medium,slow,slower,veryslow)
        recorder.setVideoOption("tune", "zerolatency");
        //编码速度，gpu编码最快是veryfast
        recorder.setVideoOption("preset", "veryfast");
        //动态码率，取值范围0-51，建议17-28，17或18即可获得视觉上与无损无区别的画面，但算法上经过有损压缩
        recorder.setVideoOption("crf", "28");

        //(2).音频参数
        //编码格式
        recorder.setAudioCodec(org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_AAC);
        //固定码率
        recorder.setAudioBitrate(320 * 1000);
        //可变比特率，1-5，1最低，5最高
        recorder.setAudioOption("vbr", "1");
        //声道
        recorder.setAudioChannels(2);
        //采样率
        recorder.setSampleRate(48000);

        //3.启动recorder
        try {
            recorder.start();
        } catch (FFmpegFrameRecorder.Exception e) {
            throw new RuntimeException(e);
        }
        return recorder;
    }

    private static Mat calculateImg(Mat img,OrtEnvironment environment,OrtSession session,String[] labels,float confThreshold,float nmsThreshold,Boolean pushVideoStream,FFmpegFrameRecorder fFmpegFrameRecorder) {

        //1.获取图像像素浮点数组
        //(1).将当前画面复制到全部变量image,以便对image进行运算
        Mat image = img.clone();
        //System.out.println("--> 当前图片基本信息，宽度:"+image.width()+",高度:"+image.height());

        //(2).转换图像大小(默认统一转为640 * 640)
        Letterbox letterbox = new Letterbox();
        image = letterbox.letterbox(image);

        image = LetterboxUtil.letterbox(image);

        //(3).转换图像模式,将图像模式由BGR(蓝绿红)转为RGB(红绿蓝)，因为opencv默认读取的是BGR格式图片，而模型需要RGB格式，所以转换成RGB格式
        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2RGB);

        //(4).图像像素归一化，转为32位浮点,1通道数(32FC1), 1. / 255代表并且把数据的取值范围0-255压缩到0-1的取值范围
        image.convertTo(image, CvType.CV_32FC1, 1. / 255);

        //(5).初始化图片像素的浮点数组，3通道，宽和高的像素都是640,，所以浮点数组的最大尺寸初始化为3 * 640 * 640即1228800
        float[] whc = new float[3 * 640 * 640];

        //(6).以whc(宽高通道数)的方式获取图像中的像素点，即将图像的所有像素点保存到whc浮点数组中
        image.get(0, 0, whc);

        //(7).将whc(宽高通道数)的方式转换为cwh(通道数宽高)的浮点存储方式，因为算法模型需要cwh(通道数宽高)的浮点数组用以计算
        float[] chw = whc2cwh(whc);

        //(8).将图像像素浮点数组转换浮点缓冲对象FloatBuffer
        FloatBuffer inputBuffer = FloatBuffer.wrap(chw);


        //2.通过模型计算图像
        OnnxTensor tensor;
        OrtSession.Result output;
        float[][] outputData;
        try {
            //(1).将浮点缓冲inputBuffer转换为模型的输入张量
            tensor = OnnxTensor.createTensor(environment, inputBuffer, new long[]{1, 3, 640, 640});

            //(2).将输入张量保存到HashMap
            HashMap<String, OnnxTensor> stringOnnxTensorHashMap = new HashMap<>();
            stringOnnxTensorHashMap.put(session.getInputInfo().keySet().iterator().next(), tensor);

            //(3).运行推理(模型推理本质是多维矩阵运算，而GPU是专门用于矩阵运算，占用率低，如果使用cpu也可以运行，可能占用率100%属于正常现象)
            output = session.run(stringOnnxTensorHashMap);

            //(4).获取到推理结果并缓存结果
            outputData = (float[][]) output.get(0).getValue();
        } catch (OrtException e) {
            throw new RuntimeException(e);
        }


        //3.处理同一帧画面检测到的多个目标(比如共检测到19目标，其中16个Car,3个Person，需要对Car和Person分别处理)
        //System.out.println("当前这一帧画面检测到的目标数量:"+outputData.length);
        //(1).获取到所有的检测结果对象
        Map<Integer, List<float[]>> class2Bbox = new HashMap<>();
        for (float[] bbox : outputData) {

            //(a).获取到评分(置信度)，评分小于阈值则跳过
            Float score = bbox[6];
            if(score < confThreshold){
                continue;
            }

            //(b).跳过无效的检测结果，即左上角的x坐标大于右下角的x坐标，或者左上角的y坐标大于右下角的y坐标
            if (bbox[1] >= bbox[3] || bbox[2] >= bbox[4]) {
                continue;
            }

            //(c).获取到当前目标的分类号
            int clsId = (int)bbox[5];
            String boxName = labels[clsId];
            //System.out.println(Arrays.toString(bbox)+"   "+ boxName);

            //(d).以分类号clsId作为key，目标检测作为value存入到map中
            class2Bbox.putIfAbsent(clsId, new ArrayList<>());

            //(e).将分类号clsId相同的目标全部加入到一个list中(比如同一张图片中有16个car的目标检测框，将16个全部存入list，然后以car的分类号作为key存入到Map)
            class2Bbox.get(clsId).add(bbox);
        }

        //(2).将分类好的目标检测框map通过nms计算去除重复边框
        List<Detection> detectionList = new ArrayList<>();
        for (Map.Entry<Integer, List<float[]>> entry : class2Bbox.entrySet()) {

            //(a).获取到当前分类号对应的多个画框(比如在本张图片上Car类型有16个画框,nms的目的是将16个中重复度高的画框给去除掉)
            List<float[]> bboxes = entry.getValue();

            //(b).通过nms算法去除边框重叠
            bboxes = nonMaxSuppression(bboxes, nmsThreshold);

            //(c).将去除重叠后的画框构建成检测结果对象detection，并加入到列表
            for (float[] bbox : bboxes) {
                String labelString = labels[entry.getKey()];
                detectionList.add(new Detection(labelString, entry.getKey(), Arrays.copyOfRange(bbox, 1, 6), bbox[6]));
            }
        }

        //(3).向当前这一帧的图像绘制所有的边框和文字
        for (Detection detection : detectionList) {
            float[] bbox = detection.getBbox();
            drawDorderAndText(img,bbox,letterbox,labels,true);
        }

        //4.边框绘制完成后，将当前图片保存到指定文件夹(或者推送到消息队列)
        if(!ObjUtil.isEmpty(detectionList)){
            Imgcodecs.imwrite("video/images/"+ DateUtil.getDateTime24String()+ UUIDUtil.getUUID(4)+".jpg", img);

            */
/*if(pushVideoStream){
                pushStreamToServer(fFmpegFrameRecorder,img);
            }*//*

        }
        return img;
    }

    private static void drawDorderAndText(Mat img,float[] bbox,Letterbox letterbox,String[] labels,Boolean writeText){

        //1.获取到视频宽和高尺寸更小的一边
        int minDwDh = Math.min(img.width(), img.width());
        //(1).画框粗细
        int thickness = minDwDh / ODConfig.lineThicknessRatio;
        //(1).字体大小
        double fontSize = minDwDh / ODConfig.fontSizeRatio;
        //(3).字体样式
        int fontFace = Imgproc.FONT_HERSHEY_SIMPLEX;

        //2.定义画框位置和颜色
        //(1).画框左上角坐标
        Point topLeft = new Point((bbox[0] - LetterboxUtil.getDw()) / LetterboxUtil.getRatio(), (bbox[1] - LetterboxUtil.getDh()) / LetterboxUtil.getRatio());
        //(2).画框右下角坐标
        Point bottomRight = new Point((bbox[2]  - LetterboxUtil.getDw()) / LetterboxUtil.getRatio(), (bbox[3]  - LetterboxUtil.getDh()) / LetterboxUtil.getRatio());
        //(3).画框颜色
        Scalar color = new Scalar(ODConfig.getOtherColor((int)bbox[4]));

        //3.将画框以矩形(rectangle)绘制当前图像上(img)
        Imgproc.rectangle(img, topLeft, bottomRight, color, thickness);

        //4.在画框框上方写文字
        if(writeText){
            //(1).文字内容(根据分类id从标签数组中获取，比如检测到的目前是安全帽，画框上方文字就写helmet)
            String boxName = labels[(int)bbox[4]];
            //(2).文字的位置(设置到画框的上方)
            Point boxNameLoc = new Point((bbox[0] - LetterboxUtil.getDw()) / LetterboxUtil.getRatio(), (bbox[1] - LetterboxUtil.getDh()) / LetterboxUtil.getRatio() - 3);
            //(3).将文字绘制当前图像      上(img)(也可以二次往视频画面上叠加其他文字或者数据，比如物联网设备数据等等)
            Imgproc.putText(img, boxName, boxNameLoc, fontFace, 0.7, color, thickness);
        }

    }

    private static List<float[]> nonMaxSuppression(List<float[]> bboxes, float iouThreshold) {

        List<float[]> bestBboxes = new ArrayList<>();

        bboxes.sort(Comparator.comparing(a -> a[4]));

        while (!bboxes.isEmpty()) {
            float[] bestBbox = bboxes.remove(bboxes.size() - 1);
            bestBboxes.add(bestBbox);
            bboxes = bboxes.stream().filter(a -> computeIOU(a, bestBbox) < iouThreshold).collect(Collectors.toList());
        }

        return bestBboxes;
    }

    private static float computeIOU(float[] box1, float[] box2) {

        float area1 = (box1[2] - box1[0]) * (box1[3] - box1[1]);
        float area2 = (box2[2] - box2[0]) * (box2[3] - box2[1]);

        float left = Math.max(box1[0], box2[0]);
        float top = Math.max(box1[1], box2[1]);
        float right = Math.min(box1[2], box2[2]);
        float bottom = Math.min(box1[3], box2[3]);

        float interArea = Math.max(right - left, 0) * Math.max(bottom - top, 0);
        float unionArea = area1 + area2 - interArea;
        return Math.max(interArea / unionArea, 1e-8f);

    }

    public static float[] whc2cwh(float[] src) {
        float[] chw = new float[src.length];
        int j = 0;
        for (int ch = 0; ch < 3; ++ch) {
            for (int i = ch; i < src.length; i += 3) {
                chw[j] = src[i];
                j++;
            }
        }
        return chw;
    }



    */
/**
     * Mat转Frame
     *//*

    public static Frame convertToFrame(Mat mat) {
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
        Frame frame = converter.convert(mat);
        return frame;
    }

    */
/**
     * Frame转Mat
     *//*

    public static Mat convertToMat(Frame frame) {
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
        Mat mat = converter.convertToOrgOpenCvCoreMat(frame);
        return mat;
    }


    */
/**
     *  将视频每一帧转换为图片
     *//*

    public static void convertVideoToImg() {
        // 加载OpenCV库
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 视频文件路径
        String videoPath = "E:/ideaGitPro/algorithmCenter/src/main/resources/video/car3.mp4"; // 替换为您的视频路径

        // 保存帧截图的文件夹路径
        String outputFolderPath = "E:/temp/img"; // 替换为您的输出文件夹路径
        File outputFolder = new File(outputFolderPath);
        outputFolder.mkdirs();


        // 打开视频文件
        try  {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
            grabber.start();

            // 遍历视频的每一帧
            int frameNumber = 0;
            Java2DFrameConverter converter = new Java2DFrameConverter();
            while (true) {
                Frame frame  =  grabber.grabImage();
                if (frame == null) {
                    break;
                }

                // 将帧转换为BufferedImage
                BufferedImage image = converter.convert(frame);

                // 保存图片
                String outputFilePath = outputFolderPath + File.separator + "frame_" + frameNumber + ".jpg";
                File outputFile = new File(outputFilePath);
                ImageIO.write(image, "jpg", outputFile);

                frameNumber++;
                Thread.sleep(1000);
            }
            grabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("帧截图保存完成！");

    }

    public static void execOpencvCvPushAndDectect(String serialNum,String videoUrl,String streamServerUrl) throws OrtException {

        //String videoUrl = "rtsp://222.210.127.152:554/rtp/51011303001187000323_51011399701327100461";
        //String videoUrl = "rtmp://rtmp03open.ys7.com:1935/v3/openlive/C23417894_1_1?expire=1721459499&id=604329999037632512&t=139b1c456d8ae3df11063825a78ff5746834704d2199c6548bceda8370511ff6&ev=100";
        byte[] modelBytes;
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            String modelPath = "src\\main\\resources\\model\\yolov7-tiny.onnx";
            modelBytes = FileUtil.getBytes(modelPath);
        }else {
            String modelPath = "/data/app/model/yolov7-tiny.onnx";
            modelBytes = FileUtil.getBytes(modelPath);
        }


        String[] labels = {
                "person", "bicycle", "car", "motorcycle", "airplane", "bus", "train",
                "truck", "boat", "traffic light", "fire hydrant", "stop sign", "parking meter",
                "bench", "bird", "cat", "dog", "horse", "sheep", "cow", "elephant", "bear",
                "zebra", "giraffe", "backpack", "umbrella", "handbag", "tie", "suitcase",
                "frisbee", "skis", "snowboard", "sports ball", "kite", "baseball bat",
                "baseball glove", "skateboard", "surfboard", "tennis racket", "bottle",
                "wine glass", "cup", "fork", "knife", "spoon", "bowl", "banana", "apple",
                "sandwich", "orange", "broccoli", "carrot", "hot dog", "pizza", "donut",
                "cake", "chair", "couch", "potted plant", "bed", "dining table", "toilet",
                "tv", "laptop", "mouse", "remote", "keyboard", "cell phone", "microwave",
                "oven", "toaster", "sink", "refrigerator", "book", "clock", "vase", "scissors",
                "teddy bear", "hair drier", "toothbrush"};
        Integer detectSkip = 12;
        float confThreshold = 0.65F;
        float nmsThreshold = 0.55F;
        opencvCvPushStreamAndDectect(serialNum,videoUrl,streamServerUrl,modelBytes,labels,detectSkip,confThreshold,nmsThreshold);
    }

    public static void execjavaCvPushAndDectect(String serialNum,String videoUrl,String streamServerUrl) throws OrtException {

        //String videoUrl = "rtsp://222.210.127.152:554/rtp/51011303001187000323_51011399701327100461";
        //String videoUrl = "rtmp://rtmp03open.ys7.com:1935/v3/openlive/C23417894_1_1?expire=1721459499&id=604329999037632512&t=139b1c456d8ae3df11063825a78ff5746834704d2199c6548bceda8370511ff6&ev=100";
        byte[] modelBytes;
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            String modelPath = "src\\main\\resources\\model\\yolov7-tiny.onnx";
            modelBytes = FileUtil.getBytes(modelPath);
        }else {
            String modelPath = "/data/app/model/yolov7-tiny.onnx";
            modelBytes = FileUtil.getBytes(modelPath);
        }


        String[] labels = {
                "person", "bicycle", "car", "motorcycle", "airplane", "bus", "train",
                "truck", "boat", "traffic light", "fire hydrant", "stop sign", "parking meter",
                "bench", "bird", "cat", "dog", "horse", "sheep", "cow", "elephant", "bear",
                "zebra", "giraffe", "backpack", "umbrella", "handbag", "tie", "suitcase",
                "frisbee", "skis", "snowboard", "sports ball", "kite", "baseball bat",
                "baseball glove", "skateboard", "surfboard", "tennis racket", "bottle",
                "wine glass", "cup", "fork", "knife", "spoon", "bowl", "banana", "apple",
                "sandwich", "orange", "broccoli", "carrot", "hot dog", "pizza", "donut",
                "cake", "chair", "couch", "potted plant", "bed", "dining table", "toilet",
                "tv", "laptop", "mouse", "remote", "keyboard", "cell phone", "microwave",
                "oven", "toaster", "sink", "refrigerator", "book", "clock", "vase", "scissors",
                "teddy bear", "hair drier", "toothbrush"};
        Integer detectSkip = 5;
        float confThreshold = 0.55F;
        float nmsThreshold = 0.55F;
        javaCvPushStreamAndDectect(serialNum,videoUrl,streamServerUrl,modelBytes,labels,detectSkip,confThreshold,nmsThreshold);
    }

    public static void main(String[] args) throws OrtException {

        String videoUrl = "E:/ideaGitPro/algorithmCenter/src/main/resources/video/car3.mp4";
        //String videoUrl = "rtsp://222.210.127.152:554/rtp/51011303001187000323_51011399701327100461";
        String pushStreamServerUrl = "rtmp://222.210.127.152:1935/stream/live/" + "3535" + "?sign=41db35390ddad33f83944f44b8b75ded";


        execOpencvCvPushAndDectect("3355",videoUrl,pushStreamServerUrl);

        //execjavaCvPushAndDectect("8282",videoUrl,pushStreamServerUrl);

        //pushStream("3355",videoUrl,pushStreamServerUrl);

    }


}
*/
