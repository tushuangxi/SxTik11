package com.tushuangxi.smart.tv.lding.widget.listener;

import android.os.CountDownTimer;
import android.view.View;



/**
 * Created by Classichu on 2017-6-9.
 *
 *
 */

/**
 * 点击变灰定时恢复，变相防止重复点击的监听
 *
 */
//protected void setOnViewClickEnabledListener(View view, final OnViewClickEnabledListener onViewClickEnabledListener) {
//        if (view != null) {
//        view.setOnClickListener(onViewClickEnabledListener);
//        }
//        }

public abstract class OnViewClickEnabledListener implements View.OnClickListener {
    private static final int CLICK_DELAY = 300;
    private CountDownTimer countDownTimer;
    private void start(final View view) {
        view.setEnabled(false);
        if (countDownTimer != null) {
            countDownTimer.cancel();
            //防止new出多个导致时间跳动加速
            countDownTimer = null;
        }
        countDownTimer = new CountDownTimer(CLICK_DELAY, CLICK_DELAY) {
            @Override
            public void onTick(long millisUntilFinished) {
//                CLog.i("onTick"+ String.valueOf(millisUntilFinished));
            }

            @Override
            public void onFinish() {
//                CLog.i("onFinish");
                view.setEnabled(true);
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            start(v);
            onViewClick(v);
        }
    }

    protected abstract void onViewClick(View v);
}
