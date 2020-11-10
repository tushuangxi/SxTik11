package com.tushuangxi.smart.tv.lding.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ToolsAllUtils {

    //电子白板  包名是com.dazzle.whiteboard  启动首页是 com.dazzle.whiteboard.WhiteBoardActivity
    public static final String WHILE_BOARD = "com.dazzle.whiteboard";
    public static final String WHILE_BOARD_UI = "com.dazzle.whiteboard.WhiteBoardActivity";


    public static final String ECLOUD = "com.ecloud.eshare.server";
    public static final String ECLOUD_UI = "com.ecloud.eshare.server.CifsClientActivity";

    public static void startWhiteBoard(Context context){
        try {
            Intent intent = new  Intent();
            intent.setComponent(new ComponentName(WHILE_BOARD, WHILE_BOARD_UI));
            context.startActivity(intent);
        }catch (Exception e) {
            e.printStackTrace();
            TipUtil.newThreadToast("未找到电子白板");
        }
    }

    //腾讯视频会议
    public static void startTencentMeeting(Context context){
        try {
            Uri uri = Uri.parse("wemeet://page");
            Intent intent = new  Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }catch (Exception e) {
            e.printStackTrace();
            TipUtil.newThreadToast("未找到视频会议");
        }

    }

    public static void startEcloud(Context context){
        if (AppUtils2.isAppInstalled(ECLOUD)){
            Intent LaunchIntentMeeting = context.getPackageManager().getLaunchIntentForPackage(ECLOUD);
            context.startActivity(LaunchIntentMeeting);
            try {
//                    Intent intent = new  Intent();
//                    intent.setComponent(new ComponentName(ECLOUD, ECLOUD_UI));
//                    context.startActivity(intent);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            TipUtil.newThreadToast("未找到无线投屏");
        }
    }

    public static void startEcloud2(Context context){
        if (AppUtils2.isAppInstalled(ECLOUD)){
//            Intent LaunchIntentMeeting = context.getPackageManager().getLaunchIntentForPackage(ECLOUD);
//            context.startActivity(LaunchIntentMeeting);
            try {
                    Intent intent = new  Intent();
                    intent.setComponent(new ComponentName(ECLOUD, ECLOUD_UI));
                    context.startActivity(intent);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            TipUtil.newThreadToast("未找到无线投屏");
        }
    }
}
