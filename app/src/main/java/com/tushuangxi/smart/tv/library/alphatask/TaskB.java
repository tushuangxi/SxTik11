package com.tushuangxi.smart.tv.library.alphatask;

import com.alibaba.android.alpha.Task;
import com.tao.admin.loglib.Logger;

public class TaskB extends Task {

    String TAG = "TAG: "+ TaskB.class.getSimpleName()+"....";
    public TaskB() {
        super("TaskB");
    }

    @Override
    public void run() {
        Logger.w(TAG, "run task B in " + Thread.currentThread().getName());

    }
}