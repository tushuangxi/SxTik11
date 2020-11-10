package com.tushuangxi.smart.tv.lding.ui.outsidechain;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.billy.android.preloader.PreLoader;
import com.billy.android.preloader.PreLoaderWrapper;
import com.billy.android.preloader.interfaces.DataListener;
import com.billy.android.preloader.interfaces.DataLoader;
import com.fengchen.uistatus.UiStatusController;
import com.fengchen.uistatus.annotation.UiStatus;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.entity.SiteNavigationRsp;
import com.tushuangxi.smart.tv.lding.other.AppConfig;
import com.tushuangxi.smart.tv.lding.rerxmvp.base.BaseActivity;
import com.tushuangxi.smart.tv.lding.widget.Html5WebView;
import com.tushuangxi.smart.tv.library.loading.conn.LoadingApp;

import butterknife.BindView;

//网站导航 详情
public class SiteNavigationDetailsActivity extends BaseActivity {

    private UiStatusController mUiStatusController;
    //开启预加载
    private PreLoaderWrapper<String>  preLoader;

    @BindView(R.id.ll_web_view)
    LinearLayout ll_web_view;

    @BindView(R.id.web_layout)
    FrameLayout web_layout;
    Html5WebView webView;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;


    SiteNavigationRsp.ResultBean.ListBean site_ResultBean;
    @Override
    public void initBundleData(Bundle bundle) {
        if (bundle!= null){
            site_ResultBean = (SiteNavigationRsp.ResultBean.ListBean) bundle.getSerializable(AppConfig.Site_ResultBean);
        }
    }

    @Override
    public void initView() {
        mUiStatusController = UiStatusController.get().bind(findViewById(R.id.web_layout));

        WebSettings settings = webView.getSettings();
        //方式加载空白  适应h5
        settings.setDomStorageEnabled(true);
        // 关键性代码，这里要给webview添加这行代码，才可以点击之后正常播放音频。记录一下。
        settings .setMediaPlaybackRequiresUserGesture(false);
        //支持加载http和https混合模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.setWebChromeClient(new Html5WebChromeClient());
        webView.loadUrl(site_ResultBean.getUrl()+"");
        Log.d("url---1",site_ResultBean.getUrl());
    }
    @Override
    public void getHttpData(Context context) {
        initPreLoader(context);
    }

    public void initPreLoader(Context context) {
        preLoader = PreLoader.just(new Loader(), new LoaderDataListener());
        preLoader.listenData();
    }

    class Loader implements DataLoader<String> {
        @Override
        public String loadData() {

            return null;
        }
    }

    class LoaderDataListener implements DataListener<String> {
        @Override
        public void onDataArrived(String data) {

        }
    }

    // 继承 WebView 里面实现的基类
    class Html5WebChromeClient extends Html5WebView.BaseWebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // 顶部显示网页加载进度
            progressbar.setProgress(newProgress);
            if (newProgress==100){
                mUiStatusController.changeUiStatusIgnore(UiStatus.CONTENT);
                progressbar.setVisibility(View.GONE);
                web_layout.setVisibility(View.VISIBLE);
            }else {
//                progressbar.setVisibility(View.VISIBLE);
            }
        }
    }
    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            super.onProgressChanged(webView, newProgress);
            progressbar.setProgress(newProgress);
            if (newProgress==100){
                progressbar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }else {
                progressbar.setVisibility(View.VISIBLE);
            }
        }

    }

    private class GankClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (url != null){
                webView.loadUrl(url);
            }
            return true;
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;

                default:
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_site_navigation_details;
    }

    @Override
    protected View getContentViewLayoutView() {
        return null;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionType() {
        return TransitionMode.FADE;
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected boolean isResultOK() {
        return false;
    }

    @Override
    protected boolean isOpenFloatingAnimationService() {
        return false;
    }

    @Override
    protected boolean isOpenFloatingErWerMaService() {
        return false;
    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            clearWebViewCache();
            ll_web_view.removeView(webView);
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
        if (preLoader != null) {
            preLoader.destroy();
        }
    }

    public void clearWebViewCache() {
        CookieSyncManager.createInstance(LoadingApp.getContext());
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeSessionCookies(null);
            cookieManager.removeAllCookie();
            cookieManager.flush();
        } else {
            cookieManager.removeSessionCookies(null);
            cookieManager.removeAllCookie();
            CookieSyncManager.getInstance().sync();
        }
        webView.clearHistory();
        webView.clearCache(true);
    }
}
