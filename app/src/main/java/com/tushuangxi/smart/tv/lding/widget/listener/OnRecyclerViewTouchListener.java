package com.tushuangxi.smart.tv.lding.widget.listener;

import android.view.MotionEvent;
import android.view.View;


/**
 * Created by louisgeek on 2017/3/11.
 *
 *  mRecyclerView.setOnTouchListener(new OnRecyclerViewTouchListener() {
 *             @Override
 *             public void onScrollUp() {
 *
 *             }
 *
 *             @Override
 *             public void onScrollDown() {
 *                 if (ViewTool.isReachedBottom(mRecyclerView) &&
 *                         !mClassicRVHeaderFooterAdapter.isDataLoading() &&
 *                         !mClassicRVHeaderFooterAdapter.isLoadComplete()
 *                         ) {
 *                     toLoadMoreData();
 *                 }
 *             }
 *         });
 */

public abstract class OnRecyclerViewTouchListener implements View.OnTouchListener {

    private int mLastMotionX = 0;
    private int mLastMotionY = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int action = event.getAction();
        //  CLog.d("xxx:action" + action);
        switch (action) {
            //手指按下
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mLastMotionY = y;
                // CLog.d("xxx:mLastMotionY" + mLastMotionY);
                break;
            //手指移动
            case MotionEvent.ACTION_MOVE:
                // CLog.d("xxx:y" + y);
                break;
            //手指抬起
            case MotionEvent.ACTION_UP:
                //  CLog.d("xxx:ACTION_UP" + y);
                int deltaX = x - mLastMotionX;
                int deltaY = y - mLastMotionY;
                // CLog.d("xxx:deltaY" + deltaY);
                if (Math.abs(deltaY) > Math.abs(deltaX) && Math.abs(deltaY) > 300) {
                    if (deltaY > 0) {
                        // 往下拉
//                        CLog.d("xxx:往==下拉" + deltaY);
                        onScrollUp();//往上滚
                    } else {
                        //  往上拉
//                        CLog.d("xxx:往上拉" + deltaY);
                        onScrollDown();//往下滚

                        return true;//返回true  方法才能监听到 MotionEvent.ACTION_DOWN
                    }
                }
                break;
        }
        return false;
    }

    public abstract void onScrollUp();

    public abstract void onScrollDown();
}
