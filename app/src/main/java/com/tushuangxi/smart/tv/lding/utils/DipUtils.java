package com.tushuangxi.smart.tv.lding.utils;

import android.content.Context;

public class DipUtils {

    /**
     * 手机分辨率从dp的单位转成为px(像素)
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 手机分辨率从px(像素)的单位转成为dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context,float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
