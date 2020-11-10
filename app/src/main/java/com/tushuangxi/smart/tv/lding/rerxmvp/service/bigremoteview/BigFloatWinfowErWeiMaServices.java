package com.tushuangxi.smart.tv.lding.rerxmvp.service.bigremoteview;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.tushuangxi.smart.tv.lding.other.AppGlobalConsts;
import com.tushuangxi.smart.tv.lding.rerxmvp.service.navigationview.AbsMeanager;
import com.tushuangxi.smart.tv.lding.utils.SpfsUtils;
import com.tushuangxi.smart.tv.library.loading.conn.LoadingApp;

import org.greenrobot.eventbus.EventBus;

/**
 * 悬浮窗  二维码
 *
 */

public class BigFloatWinfowErWeiMaServices extends Service {

    private static WindowManager winManager;
    private WindowManager.LayoutParams wmParams;

    @Override
    public void onCreate() {
        super.onCreate();
        createBigFloatView();

    }

   private static BigFloatErWeiMaView floatErWeiMaView;
    /**
     * 初始化窗口
     */
    private void createBigFloatView() {
        winManager=(WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
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

//		wmParams.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ;
        wmParams.flags=  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ;//

        wmParams.gravity = Gravity.CENTER;
        wmParams.x = 0;
        wmParams.y = 0;
//        wmParams.width=500; //悬浮窗 窗口大小
//        wmParams.height=500;
        wmParams.width =WindowManager.LayoutParams.MATCH_PARENT ;
        wmParams.height =WindowManager.LayoutParams.MATCH_PARENT ;

        floatErWeiMaView = new BigFloatErWeiMaView(getApplicationContext(),wmParams,winManager);
        winManager.addView(floatErWeiMaView, wmParams);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String floatId = SpfsUtils.readString(LoadingApp.getContext(), AppGlobalConsts.FloatId);//悬浮活动id
        String floatQrCodeUrl = SpfsUtils.readString(LoadingApp.getContext(), AppGlobalConsts.FloatQrCodeUrl);//悬浮二维码
        if (TextUtils.isEmpty(floatId)){
            AbsMeanager.getInstance().stopErWerMaFloat();
            AbsMeanager.getInstance().stopBigErWerMaFloat();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public static void  close() {
        if (winManager!= null){
            winManager.removeView(floatErWeiMaView);
        }
        Log.d("myTest", "FloatingNavigationService  close   ....  FloatingNavigationService View  onDestroy" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
