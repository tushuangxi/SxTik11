package com.tushuangxi.smart.tv.library.router;

import android.content.Context;

/********************************
 * @name UiPageInterceptor
 * @describe UiPage拦截器.
 ********************************/
public interface UiPageInterceptor {
    /**
     * 执行拦截操作.
     * 注：1.0版本目前仅支持同步操作.
     *
     * @param context  Context
     * @param uiPage   UiPage
     * @param callback UiPageInterceptorCallback
     */
    void process(Context context, UiPage uiPage, UiPageInterceptorCallback callback);
}
