package com.tushuangxi.smart.tv.lding.http;

import android.util.Log;

import com.tushuangxi.smart.tv.BuildConfig;


public class ApiConstants {

    static String TAG = "TAG: "+ ApiConstants.class.getSimpleName()+"....";

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host = null;
        switch (hostType) {
            case HostType.TYPE_HOST_QA:
                host = ProductUtil.getHost_qa();
                break;
            case HostType.TYPE_HOST_QB:
                host = ProductUtil.getHost_qb();
                break;
            case HostType.TYPE_HOST_ONLINE:
                host = ProductUtil.getHost_online();
                break;

            default:
                break;
        }
        Log.d("myTest","请求host:"+host);
        return host;
    }
}
