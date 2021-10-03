package com.tushuangxi.smart.tv.lding.utils;

import com.tao.admin.loglib.Logger;


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
        Logger.w(TAG, "启动时间：" + (System.currentTimeMillis() - mTime));
    }
}
