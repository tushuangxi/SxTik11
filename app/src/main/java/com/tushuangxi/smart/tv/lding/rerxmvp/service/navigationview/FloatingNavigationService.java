package com.tushuangxi.smart.tv.lding.rerxmvp.service.navigationview;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import androidx.annotation.Nullable;


//菜单 悬浮
public class FloatingNavigationService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        createFloatView();


    }
    private static WindowManager wm = null;
    private WindowManager.LayoutParams wmParams = null;
    private static FloatNavigationView floatScanView;
    //悬浮view
    @SuppressLint("NewApi")
    private void createFloatView() {
        wm=(WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wmParams = new WindowManager.LayoutParams();

//        wmParams.type=2002;  //type是关键，这里的2002表示系统级窗口
//        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//		wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){//6.0+
            wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else {
            wmParams.type =  WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

        wmParams.format = PixelFormat.TRANSLUCENT;// 支持透明
//		wmParams.format= PixelFormat.RGBA_8888;//设置图片格式，效果为背景透明

//		wmParams.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ;//
        wmParams.flags=  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ;//
        wmParams.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;//
        wmParams.x = 0;
        wmParams.y = 0;
        wmParams.width=WindowManager.LayoutParams.WRAP_CONTENT; //悬浮窗 窗口大小
        wmParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
//        wmParams.width =WindowManager.LayoutParams.MATCH_PARENT ;
//        wmParams.height =WindowManager.LayoutParams.MATCH_PARENT ;

        floatScanView = new FloatNavigationView(getApplicationContext(),wmParams,wm);

        wm.addView(floatScanView, wmParams);
    }

    public static void  close() {

        if (wm!= null){
            wm.removeViewImmediate(floatScanView);
        }

        Log.d("myTest", "FloatingNavigationService  close   ....  FloatingNavigationService View  onDestroy" );
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("myTest", "FloatingNavigationService  close   ....  FloatingNavigationService View  onDestroy" );
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
