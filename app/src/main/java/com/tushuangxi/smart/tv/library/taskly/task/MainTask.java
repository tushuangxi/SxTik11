package com.tushuangxi.smart.tv.library.taskly.task;

public abstract class MainTask extends Task {

    @Override
    public boolean runOnMainThread() {
        return true;
    }
}
