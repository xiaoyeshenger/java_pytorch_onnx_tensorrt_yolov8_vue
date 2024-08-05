package cn.kafuka.messagePush;

import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/*
 * @Author zhangyong
 * @Description //信息推送助手
 * @Date 下午 4:42 2019/7/24 0024
 * @Param
 * @return
 **/
//@Component
public class PushHelper {

    @Resource
    private JPusher jPusher;

    /*
     * @Author zhangyong
     * @Description //1.单个推送(别名alias(如:readin_15900001111))
     * @Date 下午 4:44 2019/5/14 0014
     * @Param
     * @return
     **/
    public void doPush(String alias, Map<String,Object> msgMap) {

        //1.推送的内容
        JSONObject jo = new JSONObject();
        jo.put("type", "platformMsg");
        jo.put("content", msgMap);

        //2.执行消息推送
        jPusher.push(alias, jo.toJSONString());
    }

    /*
     * @Author zhangyong
     * @Description //2.多个推送
     * @Date 下午 4:44 2019/5/14 0014
     * @Param
     * @return
     **/
    public void doPush(List<String> aliasList, Map<String,Object> msgMap) {

        //1.推送的内容
        JSONObject jo = new JSONObject();
        jo.put("type", "platformMsg");
        jo.put("content", msgMap);

        //2.执行消息推送
        jPusher.push(aliasList, jo.toJSONString());
    }
}
