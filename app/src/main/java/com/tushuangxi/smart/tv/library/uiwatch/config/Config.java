package com.tushuangxi.smart.tv.library.uiwatch.config;

import android.content.Context;

/**
 * description: 用于存储配置数据
 */

public class Config {
    public static long TIME_BLOCK = 1000L;//默认超时时间1000 ms  =1s
    public static String TAG = "UiWatchDog";//默认tag
    //--帧率设置上可以通过两种方式，1.设置限制帧率 2.设置超时时间——转帧率--
    public static long FPS_BLOCK = 30L;// 默认30帧率，30是流畅帧率 60是高帧率
    public static long FPS_TIME_BLOCK = 1000L;//默认超时时间1000 ms  =1s
    public static Context CONTEXT = null;
}
