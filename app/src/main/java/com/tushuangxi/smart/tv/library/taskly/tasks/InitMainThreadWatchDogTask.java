package com.tushuangxi.smart.tv.library.taskly.tasks;

import com.tsmile.debug.MainThreadWatchDog;
import com.tushuangxi.smart.tv.library.taskly.task.Task;
import com.tushuangxi.smart.tv.library.uiwatch.config.BlockTimeBuilder;
import com.tushuangxi.smart.tv.library.uiwatch.config.UiWatchDog;

/**
 * https://github.com/TsmileAssassin/MainThread-WatchDog
 * 一个简单的看门狗在主线程中发现android耗时的操作，我们可以用它找出应用程序启动缓慢的原因或UI的原因不能及时绘制。
 */
public class InitMainThreadWatchDogTask extends Task {

    String TAG = "TAG: "+ InitMainThreadWatchDogTask.class.getSimpleName()+"....";
    @Override
    public void run() {

        // at the start point
        MainThreadWatchDog.defaultInstance().startWatch();

        // at the end point
//        MainThreadWatchDog.defaultInstance().stopWatch();

        // If it is a release version
        MainThreadWatchDog.setDebug(false);
    }
}
