package com.tushuangxi.smart.tv.library.nettyclient.entry;


import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.io.Serializable;


//请求的数据
public class SimpleData  implements Serializable {
    // 整型类型参数1
    @Protobuf(fieldType = FieldType.INT32, order = 1, required = false)
    private int iparam1;
    // 长整型类型参数1
    @Protobuf(fieldType = FieldType.INT64, order = 2, required = false)
    private long lparam1;
    // 字符串型类型参数1
    @Protobuf(fieldType = FieldType.STRING, order = 3, required = false)
    private String strparam1;

    public long getLparam1() {
        return lparam1;
    }

    public void setLparam1(long lparam1) {
        this.lparam1 = lparam1;
    }

    public int getIparam1() {
        return iparam1;
    }

    public void setIparam1(int iparam1) {
        this.iparam1 = iparam1;
    }

    public String getStrparam1() {
        return strparam1;
    }

    public void setStrparam1(String strparam1) {
        this.strparam1 = strparam1;
    }

}
