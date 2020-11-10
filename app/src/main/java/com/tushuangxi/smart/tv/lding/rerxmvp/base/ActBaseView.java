package com.tushuangxi.smart.tv.lding.rerxmvp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public interface ActBaseView {

    /**
     * 初始化Bundle传递数据
     */
    void initBundleData(final Bundle bundle);


    /**
     * 初始化View
     */
    void  initView();


    /**
     * 业务操作 获取数据
     */
    void getHttpData(final Context context);


    /**
     * 视图点击事件
     */
    void onWidgetClick(final View view);
}
