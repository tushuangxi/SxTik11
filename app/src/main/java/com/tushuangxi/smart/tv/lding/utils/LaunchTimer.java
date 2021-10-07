package com.tushuangxi.smart.tv.lding.utils;


import com.vise.log.ViseLog;

/**
 * 记录启动时间
 *  LaunchTimer.startTime();
 *
 *  LaunchTimer.endTime();
 */
public class LaunchTimer {

    static String TAG = "TAG: "+ LaunchTimer.class.getSimpleName()+"....";
    private static long mTime;

    //开启时间  startRecord
    public static void startTime() {
        mTime = System.currentTimeMillis();
    }

    //结束时间  endRecord
    public static void endTime() {
        ViseLog.w( "启动时间：" + (System.currentTimeMillis() - mTime));
    }
}
