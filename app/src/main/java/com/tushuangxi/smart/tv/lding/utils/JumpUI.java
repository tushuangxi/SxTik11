package com.tushuangxi.smart.tv.lding.utils;

import android.content.Intent;
import com.tushuangxi.smart.tv.lding.ui.InitActivity;
import com.tushuangxi.smart.tv.library.loading.conn.LoadingApp;

public class JumpUI {

    public static void goInitActivity() {
        //跳转首页  方式一。 对首页设置1. android:launchMode="singleTask" 启动模式     方式二。  或者启动前结束所有activity    ActivityUtils.finishAllActivity();
        ActivityUtils.finishAllActivity();
        Intent intent= new Intent(LoadingApp.getContext(), InitActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //如果是服务里调用，必须加入new task标识
        LoadingApp.getContext().startActivity(intent);
    }

}
