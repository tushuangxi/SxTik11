package com.tushuangxi.smart.tv.lding.rerxmvp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class UpdateAppService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();


    }

    private void updater() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
