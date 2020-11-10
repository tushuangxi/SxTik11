package com.tushuangxi.smart.tv.library.nettyclient.utils;

import android.content.Context;

import java.util.Properties;

public class LinkInfoUtil {
    //netty服务器地址(Ip)
    private final static String LINK_HOST_ADDRESS = "link.hostAddress";
    //netty服务器监听端口号(port)
    private final static String LINK_HOST_PORT = "link.port";

    public  static String nettyAddrs;
    public  static int nettyPort;

    public static void acquireLinkInfoFromProperties(Context context) {
        Properties props = loadProperties(context);
        nettyAddrs = props.getProperty(LINK_HOST_ADDRESS);
         nettyPort = Integer.parseInt(props.getProperty(LINK_HOST_PORT));
    }

    private static Properties loadProperties(Context context) {
        Properties props = new Properties();
        try {
            int id = context.getResources().getIdentifier("stlink", "raw", context.getPackageName());
            props.load(context.getResources().openRawResource(id));
        } catch (Exception e) {
//            LogUtil.writeLogError("Could not find the properties file:" + e.toString());
        }
        return props;
    }
}
