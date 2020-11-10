package com.tushuangxi.smart.tv.lding.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import com.tushuangxi.smart.tv.library.loading.conn.LoadingApp;

/**
 *  desc  用于显示提示信息用于显示提示信息
 */
public class TipUtil {

    private TipUtil() {

    }

    public static void showTipWithAction(View view, String tipText, String actionText, View.OnClickListener listener) {
        Snackbar.make(view, tipText, Snackbar.LENGTH_INDEFINITE).setAction(actionText, listener).show();
    }

    public static void showTipWithAction(View view, String tipText, String actionText, View.OnClickListener listener, int duration){
        Snackbar.make(view, tipText, duration).setAction(actionText, listener).show();
    }

    public static void showSnackTip(View view, String tipText) {
        Snackbar.make(view, tipText, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 创建一个子线程,在子线程弹出一个Toast
     * @param msg 显示的字符串
     */
    public static void newThreadToast(final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(LoadingApp.getContext(), msg, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }


    public static void newThreadToast(final int resId) {
        showToastThread2( LoadingApp.getContext().getResources().getString(resId));
    }

    /**
     * 创建一个子线程,在子线程弹出一个Toast
     */
    public static void showToastThread2(String text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(LoadingApp.getContext(), text, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }

    //解决Toast重复显示问题------------------------------------------------------
    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        @Override
        public void run() {
            mToast.cancel();
        }
    };

    public static void showToast(Context mContext, String text, int duration) {
        mHandler.removeCallbacks(r);
        if (mToast != null){
            mToast.setText(text);
        } else{
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        }
        mHandler.postDelayed(r, duration);
        mToast.show();
    }

    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }
}
