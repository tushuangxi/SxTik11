package com.tushuangxi.smart.tv.lding.http;

import android.util.Log;

import com.tushuangxi.smart.tv.BuildConfig;


public class ApiConstants {

    static String TAG = "TAG: "+ ApiConstants.class.getSimpleName()+"....";
     //BaseHost
    public static final  String BASE_HOST = getBaseHost();
    public static final  String MANAGEMENT = getManagement();
    public static final  String PANEL = getPanel();

    public static String getBaseHost(){
        String baseHost = BuildConfig.BASE_HOST;
        Log.d(TAG,"请求baseHost:"+baseHost);
        return baseHost;
    }
    public static String getManagement(){
        String management = BuildConfig.MANAGEMENT;
        Log.d(TAG,"请求management:"+management);
        return management;
    }
    public static String getPanel(){
        String panel = BuildConfig.PANEL;
        Log.d(TAG,"请求host:"+panel);
        return panel;
    }




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
