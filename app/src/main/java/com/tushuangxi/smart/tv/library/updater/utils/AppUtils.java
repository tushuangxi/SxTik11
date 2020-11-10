package com.tushuangxi.smart.tv.library.updater.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * author： liguangwei
 * date： 2019/08/27
 **/
public class AppUtils {

//    public static long getVersionCode(Context context){
//        PackageManager packageManager = context.getPackageManager();
//        try {
//            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
//                long longVersionCode = packageInfo.getLongVersionCode();
//                return longVersionCode;
//            }else{
//                return packageInfo.versionCode;
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }


    /**
     * 获取本地Apk版本名称
     * @param context 上下文
     * @return String
     */
    public static String getVersionName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TAG", e.getMessage() + "获取本地Apk版本名称失败！");
            e.printStackTrace();
        }
        return verName;
    }


    /**
     * 获取本地Apk版本号
     * @param context 上下文
     * @return int
     */
    public static int getVersionCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TAG", e.getMessage() + "获取本地Apk版本号失败！");
            e.printStackTrace();
        }
        return verCode;
    }

    public static void installApk(Context context, File targetFile){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(intent.ACTION_VIEW);
        Uri uri = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            uri = FileProvider.getUriForFile(context,context.getPackageName()+".fileprovider",targetFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }else{
            uri = Uri.fromFile(targetFile);
        }
        intent.setDataAndType(uri,"application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
