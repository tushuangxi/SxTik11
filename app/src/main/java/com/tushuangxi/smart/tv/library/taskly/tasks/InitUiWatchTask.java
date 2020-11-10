package com.tushuangxi.smart.tv.library.taskly.tasks;

import com.tushuangxi.smart.tv.library.taskly.task.Task;
import com.tushuangxi.smart.tv.library.uiwatch.config.BlockTimeBuilder;
import com.tushuangxi.smart.tv.library.uiwatch.config.UiWatchDog;


public class InitUiWatchTask extends Task {

    String TAG = "TAG: "+ InitUiWatchTask.class.getSimpleName()+"....";
    @Override
    public void run() {
        //Ui卡顿监视工具一 UiWatcherDog   https://github.com/guohaiyang1992/UiWatchDog
        // 可以精确打印导致卡顿的方法的位置(点击可跳转出问题的代码行)，可以设定打印log的tag   setTimeBlock设置超时时间多少触发卡顿监控
        UiWatchDog.getDefault().setBuilder(new BlockTimeBuilder()).setTag("simon").setTimeBlock(900).build().watchIng();
    }
}
