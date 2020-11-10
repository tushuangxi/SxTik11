package com.tushuangxi.smart.tv.lding.rerxmvp.service.remoteview;

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
import com.tushuangxi.smart.tv.library.nettyclient.netty.EchoClient;
import com.tushuangxi.smart.tv.library.nettyclient.utils.LinkInfoUtil;

import org.greenrobot.eventbus.EventBus;

import io.netty.channel.ChannelHandlerContext;

/**
 * 悬浮窗  二维码
 *
 */

public class FloatWinfowErWeiMaServices extends Service {

    private static WindowManager winManager;
    private WindowManager.LayoutParams wmParams;

    @Override
    public void onCreate() {
        super.onCreate();
        createFloatView();

    }

   private static FloatErWeiMaView floatErWeiMaView;
    /**
     * 初始化窗口
     */
    private void createFloatView() {
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
        wmParams.gravity = Gravity.LEFT| Gravity.TOP;//
        wmParams.x = 0;
        wmParams.y = 0;
        wmParams.width=WindowManager.LayoutParams.WRAP_CONTENT; //悬浮窗 窗口大小
        wmParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
//        wmParams.width =WindowManager.LayoutParams.MATCH_PARENT ;
//        wmParams.height =WindowManager.LayoutParams.MATCH_PARENT ;

        floatErWeiMaView = new FloatErWeiMaView(getApplicationContext(),wmParams,winManager);
        winManager.addView(floatErWeiMaView, wmParams);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String floatId = SpfsUtils.readString(LoadingApp.getContext(), AppGlobalConsts.FloatId);//悬浮活动id
        String floatQrCodeUrl = SpfsUtils.readString(LoadingApp.getContext(), AppGlobalConsts.FloatQrCodeUrl);//悬浮二维码
        Log.d("myTestActivity", "floatId:"+floatId+"-----floatQrCodeUrl："+floatQrCodeUrl );
        if (TextUtils.isEmpty(floatId)||TextUtils.isEmpty(floatQrCodeUrl)){
            AbsMeanager.getInstance().stopErWerMaFloat();
            AbsMeanager.getInstance().stopBigErWerMaFloat();
        }
        if (!TextUtils.isEmpty(floatId)&&!TextUtils.isEmpty(floatQrCodeUrl)){
            //netty
            connNettyLink();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    //netty---
    public static ChannelHandlerContext mChannelHandlerContext;

    private void connNettyLink() {
        LinkInfoUtil.acquireLinkInfoFromProperties(getApplicationContext());
        String host = LinkInfoUtil.nettyAddrs;
        int port = LinkInfoUtil.nettyPort;
        conn(host, port);
    }

    public static void conn(final String host, final int port) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new EchoClient(host, port, new EchoClient.EchoCallBack() {
                    @Override
                    public void onConnSucess(ChannelHandlerContext type, String result, Object msg) {
                        mChannelHandlerContext=type;
                        Log.i("NettyFloat", "onConnSucess " + result);
                    }

                    @Override
                    public void onReceive(String result) {
                        Log.i("NettyFloat", "onReceive "+"\r\n" + result);
                    }

                    @Override
                    public void onExceptionTip(String result) {
                        Log.i("NettyFloat", "onExceptionTip " + result);
                    }

                    @Override
                    public void onManualClose(String result) {
                        Log.i("NettyFloat", "onManualClose " + result);
                    }

                    @Override
                    public void onReconn() {
                        Log.i("NettyFloat", "onReconn--------------------------------------正在重连.. ");
                        conn(host, port);
                    }
                }).start();
            }
        }).start();
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
