package com.tushuangxi.smart.tv.lding.widget;

import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: njb
 * @date: 2020/10/11 0011 23:56
 * @desc: View焦点放大缩小工具类
 *
 *  FocusViewUtils.scaleView(ivRecentControl,0);
 */
public class FocusViewUtils {
    private static final float scale0 = 1f;
    private static final float scaleL = 1.1f;
    private static final float scaleS = 1.13f;
    private static final float z1 = 0f;
    private static final float z2 = 1f;
    private static final long animateDuration = 20;
    private static View lastFocus;

    /**
     * view获取焦点时放大/缩小
     *
     * @param v    焦点view
     * @param type 焦点状态view放大的类型
     */
    public static void scaleView(View v, final int type) {
        v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View v, boolean hasFocus) {
                //初始化放大倍数
                float scaleX1, scaleX2, scaleY1, scaleY2;
                scaleX1 = scaleY1 = scaleX2 = scaleY2 = scale0;
                if (type == 0) {
                    //类型为0放大倍数为1.1f
                    scaleX2 = scaleY2 = scaleL;
                }
                if (type == 1) {
                    //类型为1放大倍数为1.13f
                    scaleX2 = scaleY2 = scaleS;
                }
                //获得焦点时的状态和倍数
                if (hasFocus) {
                    lastFocus = v;
                    //在android5.0以上的效果
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        v.animate()
                                .scaleXBy(scaleX1)
                                .scaleX(scaleX2)
                                .scaleYBy(scaleY1)
                                .scaleY(scaleY2)
                                .zBy(z1)
                                .z(z2)
                                .setDuration(animateDuration);
                    } else {
                        //在android5.0以下的效果
                        v.animate()
                                .scaleXBy(scaleX1)
                                .scaleX(scaleX2)
                                .scaleYBy(scaleY1)
                                .scaleY(scaleY2)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        //防止view被遮挡
                                        v.bringToFront();
                                    }
                                })
                                .setDuration(animateDuration);
                    }
                } else {
                    //view在焦点消失后的倍数和状态
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        v.animate()
                                .scaleXBy(scaleX2)
                                .scaleX(scaleX1)
                                .scaleYBy(scaleY2)
                                .scaleY(scaleY1)
                                .zBy(z2)
                                .z(z1)
                                .setDuration(animateDuration);
                    } else {
                        v.animate()
                                .scaleXBy(scaleX2)
                                .scaleX(scaleX1)
                                .scaleYBy(scaleY2)
                                .scaleY(scaleY1)
                                .setDuration(animateDuration);
                    }
                }
            }
        });
    }


    public static void touchView(View v, final int type) {
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }
}
