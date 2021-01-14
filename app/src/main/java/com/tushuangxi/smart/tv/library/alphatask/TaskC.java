package com.tushuangxi.smart.tv.library.alphatask;

import com.alibaba.android.alpha.Task;
import com.tao.admin.loglib.Logger;

public class TaskC extends Task {

    String TAG = "TAG: "+ TaskC.class.getSimpleName()+"....";
    public TaskC() {
        super("TaskC");
    }

    @Override
    public void run() {
        Logger.w(TAG,  "run task C in " + Thread.currentThread().getName());

    }
}