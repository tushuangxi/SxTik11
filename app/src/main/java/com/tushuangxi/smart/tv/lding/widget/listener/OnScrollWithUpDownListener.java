package com.tushuangxi.smart.tv.lding.widget.listener;


import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by louisgeek on 2016/12/27.
 * RecyclerView 上移或则下移
 */

public abstract class OnScrollWithUpDownListener extends RecyclerView.OnScrollListener {

    private final int mScrollThreshold = 30;

    public abstract void onScrollUp();

    public abstract void onScrollDown();
    /**
     * 当屏幕中的 item 数量多到超出屏幕的时候，这时候的滚动是会触发 onScrolled(RecyclerView recyclerView, int dx, int dy) 方法的。
     屏幕中的 item 完全显示在屏幕中时，onScrolled(RecyclerView recyclerView, int dx, int dy) 是不会被触发的。
     * @param recyclerView
     * @param dx
     * @param dy
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (Math.abs(dy) > mScrollThreshold) {
            if (dy > 0) {
                //往上滑动   往下拉
                onScrollUp();
//                CLog.i("onScrollUp");
            } else if (dy < 0) {
                //往下滑动   往上拉
                onScrollDown();
//                CLog.i("onScrollDown");
            }
        }
    }
}
