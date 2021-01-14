package com.tushuangxi.smart.tv.library.alphatask;

import com.alibaba.android.alpha.Task;
import com.tao.admin.loglib.Logger;

public class TaskA extends Task {

    String TAG = "TAG: "+ TaskA.class.getSimpleName()+"....";
    public TaskA() {
        super("TaskA");
    }

    @Override
    public void run() {
        Logger.w(TAG, "run task A in " + Thread.currentThread().getName());

    }
}