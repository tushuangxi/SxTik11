package com.tushuangxi.smart.tv.lding.http;

import android.util.Log;

public class ApiConstants {
    //测试环境
    public static final  String HOST_HENGYUANIOT = "http://xxp.hangtianyun.net/hengyuaniot-party-building/";


    //正式环境
//    public static final  String HOST_HENGYUANIOT = "http://";


    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host = null;
        switch (hostType) {
            case HostType.TYPE_HOST_HENGYUANIOT:
                host = HOST_HENGYUANIOT;
                break;

            default:
                break;
        }
        Log.d("myTest","请求host:"+host);
        return host;
    }

}
