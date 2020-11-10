package com.tushuangxi.smart.tv.library.taskly.tasks;

import com.tushuangxi.smart.tv.library.taskly.task.Task;


public class GetDeviceIdTask extends Task {

    String TAG = "TAG: "+ GetDeviceIdTask.class.getSimpleName()+"....";
    private String mDeviceId;

    @Override
    public void run() {
        // 真正自己的代码
//        TelephonyManager tManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        mDeviceId = tManager.getDeviceId();
//        PerformanceApp app = (PerformanceApp) mContext;
//        app.setDeviceId(mDeviceId);
    }
}
