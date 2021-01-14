package com.tushuangxi.smart.tv.library.alphatask;

import com.alibaba.android.alpha.Task;
import com.tao.admin.loglib.Logger;

public class TaskE extends Task {

    String TAG = "TAG: "+ TaskE.class.getSimpleName()+"....";
    public TaskE() {
        super("TaskE");
    }

    @Override
    public void run() {
        Logger.w(TAG, "run task E in " + Thread.currentThread().getName());

    }
}