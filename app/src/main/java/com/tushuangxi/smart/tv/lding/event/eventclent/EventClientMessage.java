package com.tushuangxi.smart.tv.lding.event.eventclent;

/**
 * 此类定义SDK的回调事件,此类包含2个重要信息;
 * 1. 事件类型;
 * 2. 事件data 由于不同的事件会返回不同的data,所以data是个Object类型.
 *
 * @author Created by changliang on 2017/6/21.
 */
public class EventClientMessage {
    //事件类型
    private int event;
    //事件data 由于不同的事件会返回不同的data,所以data是个Object类型
    private Object data;

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public EventClientMessage() {
        super();
    }

    public EventClientMessage(int event) {
        super();
        this.event = event;
    }

    public EventClientMessage(int event, Object data) {
        super();
        this.event = event;
        this.data = data;
    }
}