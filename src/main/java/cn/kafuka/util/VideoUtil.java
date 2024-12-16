package cn.kafuka.util;

import cn.kafuka.bo.vo.UserVo;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class VideoUtil {

    /**
     * 视频流是否可播放
     */
    @SneakyThrows
    public static Boolean isVideoCanPlayed(String playUrl){

        boolean isAvailable = false;
        if(playUrl.endsWith(".mp4")){
            isAvailable = true;
        }

        if(playUrl.contains(":")){
            String[] split = playUrl.split(":");
            if(split.length < 3){
                throw new IllegalArgumentException("播放地址格式不对");
            }
            //协议(rtmp/rtsp/http/ws)
            String protocol = split[0];

            //主机地址
            String hostStr = split[1];
            if(!hostStr.contains(".")){
                throw new IllegalArgumentException("播放地址格式不对");
            }
            String host = hostStr.substring(2);

            //端口号
            String portStr  =split[2];
            if(portStr.contains("/")){
                int i = split[2].indexOf("/");
                portStr =split[2].substring(0,i);
            }
            Integer port = Integer.parseInt(portStr);

            Socket socket = null;

            try {
                // 创建 Socket 对象，并指定 rtmp/rtsp/http/ws 服务器的 IP 地址和端口号
                socket = new Socket();
                socket.connect(new InetSocketAddress(host,port),3000);

                // 如果连接成功，则 rtmp/rtsp/http/ws 可用
                isAvailable = true;
            } catch (IOException e) {
                // 连接失败，rtmp/rtsp/http/ws 不可用
                isAvailable = false;
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // 忽略关闭异常
                    }
                }
            }
        }

        return isAvailable;
    }


    /**
     * 视频流是否可播放
     */
    @SneakyThrows
    public static Boolean isVideoPlayUrlLegitimate(String playUrl){

        boolean isAvailable = false;
        if(playUrl.endsWith(".mp4")){
            isAvailable = true;
        }

        if(playUrl.contains(":")){
            String[] split = playUrl.split(":");
            if(split.length < 3){
                throw new IllegalArgumentException("播放地址格式不对");
            }
            //协议(rtmp/rtsp/http/ws)
            String protocol = split[0];

            //主机地址
            String hostStr = split[1];
            if(!hostStr.contains(".")){
                throw new IllegalArgumentException("播放地址格式不对");
            }
            isAvailable = true;
        }

        return isAvailable;
    }


    /**
     * 视频能够正常播放
     */
    public static boolean isVideoPlayable(String videoUrl) {
        try {
            // 构建ffmpeg命令
            String ffmpegCmd = "ffmpeg -v error -i \"" + videoUrl + "\" -f null /dev/null";
            // 执行ffmpeg命令
            Process process = Runtime.getRuntime().exec(ffmpegCmd);
            // 等待命令执行完成
            process.waitFor();
            // 获取退出值
            int exitValue = process.exitValue();
            // 如果退出值为0，则视频可以播放
            return exitValue == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 视频能够正常播放以及流媒体服务器是否能够连接成功
     * 通过ffmpeg命令实现
     */
    public static Map<String, Object> isVideoPlayableAndStreamServerCanConnected(String videoUrl,String streamServerUrl) {
        //String videoUrl = "rtmp://rtmp03open.ys7.com:1935/v3/openlive/C23417894_1_1?expire=1721459499&id=604329999037632512&t=139b1c456d8ae3df11063825a78ff5746834704d2199c6548bceda8370511ff6&ev=100";
        //String videoUrl = "http://192.168.2.241:11080/stream/live/ori_ffmpeg05.live.flv";
        //String streamServerUrl = "rtmp://192.168.2.242:11935/stream/live?sign=41db35390ddad33f83944f44b8b75ded";

        List<String> cmdList = new ArrayList<>();
        cmdList.add("ffmpeg");
        cmdList.add("-v");
        cmdList.add("error");
        cmdList.add("-re");
        cmdList.add("-i");
        cmdList.add(videoUrl);
        cmdList.add("-c");
        cmdList.add("copy");
        cmdList.add("-f");
        cmdList.add("flv");
        cmdList.add(streamServerUrl);

        Map<String, Object> stringObjectMap = ShellCommandExecutorUtil.callProcess("./", cmdList);
        return stringObjectMap;

    }



/*    public static void main(String[] args) {


        //String playUrl = "rtmp://rtmp03open.ys7.com:1935/v3/openlive/C23417894_1_1?expire=1721459499&id=604329999037632512&t=139b1c456d8ae3df11063825a78ff5746834704d2199c6548bceda8370511ff6&ev=100";
        String playUrl = "http://192.168.2.241:11080/stream/live/ori_ffmpeg05.live.flv";
        String streamServerUrl = "rtmp://192.168.2.241:11935/stream/live?sign=41db35390ddad33f83944f44b8b75ded";

        long startTime = System.currentTimeMillis();

        Map<String, Object> stringObjectMap = isVideoPlayableAndStreamServerCanConnected(playUrl,streamServerUrl);
        String msg = (String)stringObjectMap.get("msg");
        Integer code = (Integer) stringObjectMap.get("code");
        System.out.println("STEP1111------------->Video is playable msg: " + msg);
        System.out.println("STEP2222------------->Video is playable code: " + code);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:"+(endTime-startTime)+"毫秒");

        if(code != 0){
            String returnMsg = "视频播放异常/连接流媒体服务器失败";
            if(msg.contains("Server returned 404 Not Found")){
                returnMsg = "视频无法打开,请输入正确的播放地址";
            }
            if(msg.contains("Cannot open connection tcp")){
                returnMsg = "流媒体服务器连接失败,请联系管理员";
            }
            throw new IllegalArgumentException(returnMsg);
        }
    }*/
}
