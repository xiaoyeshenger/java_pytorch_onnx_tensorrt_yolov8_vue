package cn.kafuka.event;

import org.springframework.context.ApplicationEvent;

/*
 * @Author: zhangyong
 * description: 定义事件(可以异步执行的事件)
 * @Date: 2020/3/19 14:26
 * @Param:
 * @Return:
 */
public class AsyncableEvent extends ApplicationEvent {

    //默认所有的事件都为异步
    private boolean async = true;

    //创建一个事件，source为:事件最初发生时所在的对象
    public AsyncableEvent(Object source) {
        super(source);
    }

    public AsyncableEvent(Object source, boolean async) {
        super(source);
        this.async = async;
    }

    //当事件是否是异步执行
    public boolean isAsync() {
        return async;
    }

    //设置事件同步执行
    public final void sync() { async = false; }
}
