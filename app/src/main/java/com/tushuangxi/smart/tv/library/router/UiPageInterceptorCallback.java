package com.tushuangxi.smart.tv.library.router;

/********************************
 * @name UiPageInterceptorCallback
 * @describe UiPage拦截器回调.
 ********************************/
public interface UiPageInterceptorCallback {

    void onContinue(UiPage uiPage);

    void onInterrupt(int errorCode, Throwable throwable);
}
