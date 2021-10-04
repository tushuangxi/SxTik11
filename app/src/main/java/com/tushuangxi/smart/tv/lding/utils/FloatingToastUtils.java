package com.tushuangxi.smart.tv.lding.utils;

import static com.tushuangxi.smart.tv.library.loading.conn.LoadingApp.getContext;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;

import com.github.hariprasanths.floatingtoast.FloatingToast;

public class FloatingToastUtils {
    //         Typeface customFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/custom_font.ttf");

    public static void showTopToast(View view,String msg){
        FloatingToast.makeToast(view, msg, FloatingToast.LENGTH_LONG)
                .setGravity(FloatingToast.GRAVITY_MID_TOP)
                .setFadeOutDuration(FloatingToast.FADE_DURATION_LONG)
                .setTextSizeInDp(12)
                .setBackgroundBlur(true)
                .setFloatDistance(FloatingToast.DISTANCE_SHORT)
                .setTextColor(Color.parseColor("#ffffff"))
                .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
//                .setTextTypeface(customFont)
                .show();
    }

    public static void showCenterToast(View view,String msg){
        FloatingToast.makeToast(view, msg, FloatingToast.LENGTH_MEDIUM)
                .setGravity(FloatingToast.GRAVITY_CENTER)
                .setFadeOutDuration(FloatingToast.FADE_DURATION_MEDIUM)
                .setTextSizeInDp(12)
                .setBackgroundBlur(true)
                .setTextColor(Color.parseColor("#ffffff"))
                .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
//                .setTextTypeface(customFont)
                .show();
    }

    public static void showRedCenterToast(View view,String msg){
        FloatingToast.makeToast(view, msg, 750)
                .setGravity(FloatingToast.GRAVITY_CENTER)
                .setFadeOutDuration(1000)
                .setTextSizeInDp(12)
                .setBackgroundBlur(true)
                .setTextColor(Color.parseColor("#ff0000"))
//                .setTextTypeface(customFont)
                .show();
    }

    public static void showBottomToast(View view,String msg){
        FloatingToast.makeToast(view, msg, FloatingToast.LENGTH_MEDIUM)
                .setGravity(FloatingToast.GRAVITY_MID_BOTTOM)
                .setFadeOutDuration(FloatingToast.FADE_DURATION_MEDIUM)
                .setTextSizeInDp(12)
                .setBackgroundBlur(true)
                .setTextColor(Color.parseColor("#ffffff"))
                .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
//                .setTextTypeface(customFont)
                .show();
    }

    public static void showRedOnTopToast(View view,String msg){
        FloatingToast.makeToast(view, msg, FloatingToast.LENGTH_MEDIUM)
                .setTextColor(Color.parseColor("#ff0000"))
//                .setTextTypeface(customFont)
                .setTextSizeInDp(12)
                //Show toast at the touch location inside the view redOnTop
                .showAtTouchPosition(view);
    }
    public static void showWhiteOnTopToast(View view,String msg){
        FloatingToast.makeToast(view, "Lorem ipsum dolor sit amet", FloatingToast.LENGTH_MEDIUM)
                .setTextColor(Color.parseColor("#ffffff"))
//                .setTextTypeface(customFont)
                .setTextSizeInDp(12)
                .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                .showAtTouchPosition(view);
    }

    public static void showLayoutToast(View view,String msg){
        FloatingToast.makeToast(view, msg, FloatingToast.LENGTH_MEDIUM)
                .setTextColor(Color.parseColor("#0000ff"))
//                .setTextTypeface(customFont)
                .setTextSizeInDp(12)
                .showAtTouchPosition(view);
    }
}
