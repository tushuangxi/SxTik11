package com.tushuangxi.smart.tv.library.loading;

import android.app.Application;
import android.content.Context;


//初始化闪屏
public class SplashKit {

    private static SplashKit instance;

    private Application app;

    public static synchronized SplashKit getInstance() {
        if (instance == null){
            instance = new SplashKit();
        }
        return instance;
    }
    public void init(Application app) {
        this.app = app;
    }

    public Context getAppContext() {
        return this.app.getApplicationContext();
    }

}
