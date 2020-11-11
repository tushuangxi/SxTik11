package com.tushuangxi.smart.tv.lding.event.eventclent;

/**
 * @Created by changliang on 2017/6/21.
 */
public interface EventClientListener {

    /**
     * 逐步会用回调的方式替换broadcast通知,
     * 以便开发者更容易定制自己的实现例如免打扰功能.
     *
     * @param event 事件
     * @return true-处理完成,事件响应链中止;false-未处理完成,交由事件响应链条中下一响应节点继续处理该事件
     */
    public boolean onEvent(EventClientMessage event);
}
