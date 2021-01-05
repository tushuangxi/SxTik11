package com.tushuangxi.smart.tv.lding.widget.listener;

import android.view.View;

/**
 * 双击事件
 *  Created by louisgeek on 2016/12/13.
 */
public abstract class OnDoubleClickListener implements View.OnClickListener {
    private int count = 0;
    private long firClick = 0;
    private long secClick = 0;
    private static final long CLICK_DELAY = 300;
    @Override
    public void onClick(View v) {
        count++;
        if (count == 1) {
            firClick = System.currentTimeMillis();

        } else if (count == 2) {
            secClick = System.currentTimeMillis();
            if (secClick - firClick < CLICK_DELAY) {
                //双击事件
                onDoubleClick(v);
            }
            count = 0;
            firClick = 0;
            secClick = 0;
        }
    }

    protected abstract void onDoubleClick(View v);
}