package com.tushuangxi.smart.tv.lding.http;

public class ProductUtil {
    //QA
    public static String host_qa;
    //QB
    private static String host_qb ;
    //Online
    private static String host_online ;

    public static String getHost_qa() {
        return host_qa;
    }

    public void setHost_qa(String host_qa) {
        this.host_qa = host_qa;
    }

    public static String getHost_qb() {
        return host_qb;
    }

    public void setHost_qb(String host_qb) {
        this.host_qb = host_qb;
    }

    public static String getHost_online() {
        return host_online;
    }

    public void setHost_online(String host_online) {
        this.host_online = host_online;
    }
}
