package com.tushuangxi.smart.tv.library.taskly.tasks;

import android.app.Application;

import com.tushuangxi.smart.tv.library.loading.SplashKit;
import com.tushuangxi.smart.tv.library.taskly.task.Task;


/**
 * 异步的Task
 */
public class InitSplashKitTask extends Task {

    String TAG = "TAG: "+ InitSplashKitTask.class.getSimpleName()+"....";
    private Application app;

    public InitSplashKitTask(Application app) {
        super();

        this.app = app;
    }

    @Override
    public void run() {

        //初始化闪屏
        SplashKit.getInstance().init(app);

    }
}
