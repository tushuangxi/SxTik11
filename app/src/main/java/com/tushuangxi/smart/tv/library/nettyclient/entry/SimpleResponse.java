package com.tushuangxi.smart.tv.library.nettyclient.entry;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

//请求结果
public class SimpleResponse {
    // 结果码
    @Protobuf(fieldType = FieldType.INT32, order = 1, required = true)
    private int stateCode;
    // 扩展参数
    @Protobuf(fieldType = FieldType.STRING, order = 2, required = false)
    private String param;

    public static SimpleResponse valueOf(int stateCode) {
        SimpleResponse obj = new SimpleResponse();
        obj.setStateCode(stateCode);
        return obj;
    }

    public static SimpleResponse valueOf(int stateCode, String param) {
        SimpleResponse obj = new SimpleResponse();
        obj.setStateCode(stateCode);
        obj.setParam(param);
        return obj;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }


}
