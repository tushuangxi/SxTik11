package com.tushuangxi.smart.tv.library.taskly.tasks;

import android.content.Context;
import com.tushuangxi.smart.tv.library.imageloaderfactory.ImageLoaderUtils;
import com.tushuangxi.smart.tv.library.imageloaderfactory.cofig.FileUtils;
import com.tushuangxi.smart.tv.library.taskly.task.Task;

public class InitImageLoadTask extends Task {

    String TAG = "TAG: "+ InitImageLoadTask.class.getSimpleName()+"....";
    private Context mContext;

    public InitImageLoadTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void run() {
        initImageLoadFactory();
    }

    private void initImageLoadFactory() {
        ImageLoaderUtils.init(mContext, FileUtils.getImageTmpDir(), true);
    }
}
