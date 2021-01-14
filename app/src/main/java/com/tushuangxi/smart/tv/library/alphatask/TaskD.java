package com.tushuangxi.smart.tv.library.alphatask;

import com.alibaba.android.alpha.Task;
import com.tao.admin.loglib.Logger;

public class TaskD extends Task {

    String TAG = "TAG: "+ TaskD.class.getSimpleName()+"....";
    public TaskD() {
        super("TaskD");
    }

    @Override
    public void run() {
        Logger.w(TAG,"run task D in " + Thread.currentThread().getName());

    }
}