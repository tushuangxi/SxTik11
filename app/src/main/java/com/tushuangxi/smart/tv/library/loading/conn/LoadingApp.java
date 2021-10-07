package com.tushuangxi.smart.tv.library.loading.conn;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import androidx.multidex.MultiDex;
import com.tencent.mmkv.MMKV;
import com.tushuangxi.smart.tv.lding.other.CommonLibConstant;
import com.tushuangxi.smart.tv.lding.rerxmvp.service.navigationview.AbsMeanager;
import com.tushuangxi.smart.tv.lding.utils.AppUtils2;
import com.tushuangxi.smart.tv.lding.utils.LaunchTimer;
import com.lky.toucheffectsmodule.TouchEffectsManager;
import com.lky.toucheffectsmodule.types.TouchEffectsViewType;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;
import com.tushuangxi.smart.tv.lding.utils.LogcatUtils;
import com.vise.log.ViseLog;
import com.vise.log.inner.DefaultTree;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LoadingApp
 */
public class LoadingApp extends Application {

    String TAG = "TAG: "+ LoadingApp.class.getSimpleName()+"....";
    static {
        TouchEffectsManager.build(TouchEffectsWholeType.SCALE)
                .addViewType(TouchEffectsViewType.ALL)
                .setListWholeType(TouchEffectsWholeType.RIPPLE)
                .setAspectRatioType(4f,TouchEffectsWholeType.RIPPLE);//宽高比大于4时启动水波纹

    }

    // 是否为主进程
    private boolean isMainProcess;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        LaunchTimer.startTime();
        mContext = getApplicationContext();
        isMainProcess = TextUtils.equals(getPackageName(), AppUtils2.getProcessName(mContext));
        if (isMainProcess) {
            //屏幕适配
            String jsonData = "{\"benefitProgramGuide\":{\"benefitProgramGuideId\":\"c184fd43748c40f1826abdd331a62077\",\"benefitProgramUnitList\":[{\"benefitImgAssets\":[{\"benefitImgAssetId\":\"5969749407010816\",\"duration\":30,\"imgDigest\":\"9bc0bdcc80834b6ea6a22de0953e634a\",\"imgUrl\":\"http://down.loudingsoft.com/20201009/f245967d45a5420d92af1271b2e0f4e9.png?e=1605074951&token=t0kVJ7T6T9LgPaQTqzRtioL0a_d8XJH6vO4ly-ht:8_zsY83013R_WgbACG8TzzXkHw0=\",\"location\":1,\"sort\":1},{\"benefitImgAssetId\":\"5969749407076352\",\"duration\":30,\"imgDigest\":\"d77f64af6c4835e906afb48424078302\",\"imgUrl\":\"http://down.loudingsoft.com/20201009/2b529c50bbd14b6eb22c580a61ad3b1e.png?e=1605074951&token=t0kVJ7T6T9LgPaQTqzRtioL0a_d8XJH6vO4ly-ht:q8CwCwcnI_bjeBsLMcuKNg1wBdk=\",\"location\":1,\"sort\":2}],\"benefitProgramUnitId\":\"5969743277527040\",\"duration\":30,\"templateSign\":\"TWO_SPLIT_SCREEN_LOWER_VIDEO_16:9\",\"videoDigest\":\"11bb04bade297b67aa3e39c13a2720cb\",\"videoUrl\":\"http://down.loudingsoft.com/20201009/f676da33613d4d9cb3796fb37b0ee443.mp4?e=1605074951&token=t0kVJ7T6T9LgPaQTqzRtioL0a_d8XJH6vO4ly-ht:6seRdH-WY9J5hj6iaw5fIZ03ddA=\"}],\"endDate\":\"20201012235959\",\"startDate\":\"20201012000000\"},\"stateCode\":1}";

            ViseLog.getLogConfig()
                    .configAllowLog(true)//是否输出日志
                    .configShowBorders(true)//是否排版显示
                    .configTagPrefix("ViseLog")//设置标签前缀
                    .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")//个性化设置标签，默认显示包名//%d	当前时间  %t当前线程名称  %c线程信息和类路径
                    .configLevel(Log.VERBOSE);//设置日志最小输出级别，默认Log.VERBOSE
            ViseLog.plant(new DefaultTree());//添加打印日志信息到Logcat的树

            //打印基本信息
            ViseLog.d("test message");
            //打印基本对象
            ViseLog.d(new Boolean(true));
            //打印Bundle对象
            ViseLog.d(new Bundle());
            //打印Intent对象
            ViseLog.d(new Intent());
            //打印Reference对象
            ViseLog.d(new SoftReference(0));
            //打印Throwable对象
            ViseLog.e(new NullPointerException("this object is null!"));
            //打印List集合
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                list.add("test" + i);
            }
            ViseLog.d(list);
            //打印Map集合
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < 5; i++) {
                map.put("xyy" + i, "test" + i);
            }
            ViseLog.d(map);
            //打印JSON字符串
            ViseLog.json(jsonData);
            //打印XML字符串
            String xml = "<xyy><test1><test2>key</test2></test1><test3>name</test3><test4>value</test4></xyy>";
            ViseLog.xml(xml);
        }

        //common依赖库的相关初始化
        CommonLibConstant.init()
                .setAppContext(mContext)
                .setIsDebug(true)
                .setIsShowLogcat(true) //是否显示悬浮  logcat
                .setNoNetWorkRemind("无网络")
                .setSharedPreferencesName("sp_app");

        //枚举 初始化 任务
        PreTaskManager.$.init(this,getContext().getApplicationContext());

        //初始化mmkv
        MMKV.initialize(this);
    }

    //创建一个静态的方法，以便获取context对象
    public static Context getContext(){
        return mContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //解决  dex的方法数量被限制在65535之内，这就是著名的64K(64*1024)事件
        MultiDex.install(base);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }


}
