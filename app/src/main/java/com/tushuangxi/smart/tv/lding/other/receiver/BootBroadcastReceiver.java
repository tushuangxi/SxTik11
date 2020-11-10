package com.tushuangxi.smart.tv.lding.other.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tao.admin.loglib.Logger;


public class BootBroadcastReceiver extends BroadcastReceiver {
    String TAG = BootBroadcastReceiver.class.getSimpleName()+"....";
        //普通开机广播
        static final String ACTION = "android.intent.action.BOOT_COMPLETED";


    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.w(TAG,"开机启动");
        if (intent.getAction().equals(ACTION)) {

//            Intent bootBroadcastIntent = new Intent(context, InitActivity.class);
//            bootBroadcastIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(bootBroadcastIntent);
        }
    }
}
