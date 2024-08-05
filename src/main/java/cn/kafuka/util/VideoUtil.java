package cn.kafuka.util;

import cn.kafuka.bo.vo.UserVo;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URLDecoder;


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

/*    public static void main(String[] args) {
        String playUrl = "rtmp://rtmp03open.ys7.com:1935/v3/openlive/C23417894_1_1?expire=1721459499&id=604329999037632512&t=139b1c456d8ae3df11063825a78ff5746834704d2199c6548bceda8370511ff6&ev=100";
        long startTime = System.currentTimeMillis();
        Boolean videoCanPlayed = isVideoCanPlayed(playUrl);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:"+(endTime-startTime)+"毫秒");
        System.out.println("是否可播放:"+videoCanPlayed);
    }*/
}
