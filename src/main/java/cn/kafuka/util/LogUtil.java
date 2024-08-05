package cn.kafuka.util;

/**
 * 处理并记录日志文件
 * 
 * @author ruoyi
 */
public class LogUtil
{
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
