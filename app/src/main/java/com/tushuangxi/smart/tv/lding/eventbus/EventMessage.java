package com.tushuangxi.smart.tv.lding.eventbus;


//(消息体)
public class EventMessage {

    private int code;//标记
    private int position;
    private String msgStr;

    public EventMessage(int code, int position, String msgStr) {
        this.code = code;
        this.position = position;
        this.msgStr = msgStr;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMsgStr() {
        return msgStr;
    }

    public void setMsgStr(String msgStr) {
        this.msgStr = msgStr;
    }
}
