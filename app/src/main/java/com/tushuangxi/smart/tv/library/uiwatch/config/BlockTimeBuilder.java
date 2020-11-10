package com.tushuangxi.smart.tv.library.uiwatch.config;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.tushuangxi.smart.tv.library.uiwatch.core.BlockPrinter;


/**
 * description: 阻塞时间builder
 */

public class BlockTimeBuilder extends IBuilder {
    private String tag = Config.TAG;
    private long timeBlock = Config.TIME_BLOCK;


    @Override
    void watchIng() {
        Looper.getMainLooper().setMessageLogging(BlockPrinter.getDefault());
    }

//    @Override
//    protected long getFpsBlock() {
//        return 0;
//    }

    @Override
    protected long getTimeBlock() {
        return timeBlock;
    }

    @Override
    protected String getTag() {
        return tag;
    }

    @Override
    public IBuilder setTag(String tag) {
        if (TextUtils.isEmpty(tag)) {
            Log.e(Config.TAG, "当前设置的tag无效，将使用默认tag: UiWatchDog !");
        } else {
            this.tag = tag;
        }
        return this;
    }

    @Override
    public IBuilder setTimeBlock(long timeBlock) {
        this.timeBlock = timeBlock;
        return this;
    }

//    @Override
//    public IBuilder setFpsBlock(long fpsBlock) {
//        return this;
//    }


}
