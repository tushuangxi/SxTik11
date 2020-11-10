package com.tushuangxi.smart.tv.library.nettyclient.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.tushuangxi.smart.tv.library.nettyclient.constant.CmdCode;
import com.tushuangxi.smart.tv.library.nettyclient.entry.SimpleData;

/**
 * @Desc: 设备管理控制
 */
public class ControllerTask implements Runnable {
    private short cmd;
    private byte[] data;

    public ControllerTask( short cmd, byte[] data) {
        this.cmd = cmd;
        this.data = data;
    }

    @Override
    public void run() {
        SimpleData simpleData = JProtobufUtil.decode(data, SimpleData.class);
        switch (cmd) {
            //处理方式一  消息处理
            case CmdCode.CMD_TC_GET_APP_SCREENSHOT://截屏
                Message message = Message.obtain();
                message.what = SCREEN;
                message.obj = simpleData;
                handler.sendMessage(message);
                break;

            //处理方式二  直接处理
            case CmdCode.CMD_TC_RTMPPUSH_OPEN_OF_CLOSE: // 电梯视频监控  RtmpPushService  开启
//                DevManagerFactory.getDevManager().startRtmpPushService();
                break;

            default:
                break;
        }
    }

    private static final int SCREEN = 1001;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SCREEN) {
//                LogUtil.writeDebugInfo("tcy", "handler ScreenShotCommand" + ((ScreenShotCommand) msg.obj).toString());

              //收到截屏指令  ....后面处理 这个业务就行了
              // screenShots((SimpleData) msg.obj);
                Log.e("Netty", "收到截屏指令 " + (SimpleData) msg.obj);
            }
        }
    };

}
