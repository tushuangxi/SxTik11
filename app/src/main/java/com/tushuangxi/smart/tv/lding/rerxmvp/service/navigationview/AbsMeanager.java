package com.tushuangxi.smart.tv.lding.rerxmvp.service.navigationview;

import android.content.Intent;
import android.util.Log;

import com.tushuangxi.smart.tv.lding.rerxmvp.service.bigremoteview.BigFloatWinfowErWeiMaServices;
import com.tushuangxi.smart.tv.lding.rerxmvp.service.remoteview.FloatWinfowErWeiMaServices;
import com.tushuangxi.smart.tv.library.loading.conn.LoadingApp;

public class AbsMeanager {

    private volatile static AbsMeanager mInstance;

    public  AbsMeanager() {

    }

    public static AbsMeanager initClient() {
        if (mInstance == null) {
            synchronized (AbsMeanager.class) {
                if (mInstance == null) {
                    mInstance = new AbsMeanager();
                }
            }
        }
        return mInstance;
    }

    public static AbsMeanager getInstance() {
        return initClient();
    }

    /**
     * 菜单 悬浮  开启
     */
    Intent  intentFloating;
    public void startFloatingAnimation() {
        try {
            if (intentFloating==null){
                intentFloating = new Intent(LoadingApp.getContext(), FloatingNavigationService.class);
                LoadingApp.getContext().startService(intentFloating);
                Log.d("myTest", "FloatingNavigationService   start ......");
            }
        }catch (Exception e){
            Log.d("myTest","FloatingNavigationService   catch 捕获 ......");
        }
    }

    /**
     * 菜单 悬浮  关闭
     */
    public void stopFloatingAnimation() {
        try {
            synchronized (AbsMeanager.class){
                if (intentFloating!=null){
                    FloatingNavigationService.close();
                    LoadingApp.getContext().stopService(intentFloating);
                    Log.d("myTest","FloatingNavigationService   close ......");
                    intentFloating=null;
                }else {
                    Log.d("myTest","FloatingNavigationService   Not Started ......");
                }
            }
        }catch (Exception e){
            Log.d("myTest","FloatingNavigationService   catch 捕获 ......");
        }
    }




    /**
     * 悬浮窗  二维码  开启
     */
    Intent  intentErWerMaFloat;
    public void startErWerMaFloat() {
        try {
            if (intentErWerMaFloat==null){
                intentErWerMaFloat = new Intent(LoadingApp.getContext(), FloatWinfowErWeiMaServices.class);
                LoadingApp.getContext().startService(intentErWerMaFloat);
                Log.d("myTest", "FloatWinfowErWeiMaServices   start ......");
            }
        }catch (Exception e){
            Log.d("myTest","FloatWinfowErWeiMaServices   catch 捕获 ......");
        }
    }
    /**
     *  悬浮窗  二维码  关闭
     */
    public void stopErWerMaFloat() {
        try {
            synchronized (AbsMeanager.class){
                if (intentErWerMaFloat!=null){
                    FloatWinfowErWeiMaServices.close();
                    LoadingApp.getContext().stopService(intentErWerMaFloat);
                    Log.d("myTest","FloatWinfowErWeiMaServices   close ......");
                    intentErWerMaFloat=null;
                }else {
                    Log.d("myTest","FloatWinfowErWeiMaServices   Not Started ......");
                }
            }
        }catch (Exception e){
            Log.d("myTest","FloatingNavigationService   catch 捕获 ......");
        }
    }
    //-----------------------------------------------------------------------------------------------big
    /**
     * 悬浮窗  二维码  开启
     */
    Intent  intentBigErWerMaFloat;
    public void startBigErWerMaFloat() {
        try {
            if (intentBigErWerMaFloat==null){
                intentBigErWerMaFloat = new Intent(LoadingApp.getContext(), BigFloatWinfowErWeiMaServices.class);
                LoadingApp.getContext().startService(intentBigErWerMaFloat);
                Log.d("myTest", "BigFloatWinfowErWeiMaServices   start ......");
            }
        }catch (Exception e){
            Log.d("myTest","BigFloatWinfowErWeiMaServices   catch 捕获 ......");
        }
    }
    /**
     *  悬浮窗  二维码  关闭
     */
    public void stopBigErWerMaFloat() {
        try {
            synchronized (AbsMeanager.class){
                if (intentBigErWerMaFloat!=null){
                    BigFloatWinfowErWeiMaServices.close();
                    LoadingApp.getContext().stopService(intentBigErWerMaFloat);
                    Log.d("myTest","BigFloatWinfowErWeiMaServices   close ......");
                    intentBigErWerMaFloat=null;
                }else {
                    Log.d("myTest","BigFloatWinfowErWeiMaServices   Not Started ......");
                }
            }
        }catch (Exception e){
            Log.d("myTest","BigFloatingNavigationService   catch 捕获 ......");
        }
    }
    //-----------------------------------------------------------------------------------------------  study  生成二维码 开始活动

}
