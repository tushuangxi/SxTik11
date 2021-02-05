package com.tushuangxi.smart.tv.lding.ui.outsidechain;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
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
public class TestSidectivity extends BaseActivity {

    private UiStatusController mUiStatusController;
    //开启预加载
    private PreLoaderWrapper<String>  preLoader;

    @BindView(R.id.ll_web_view)
    LinearLayout ll_web_view;

    @BindView(R.id.web_layout)
    FrameLayout web_layout;

    @BindView(R.id.webView)
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
//        webView.loadUrl(site_ResultBean.getUrl()+"");
//        Log.d("url---1",site_ResultBean.getUrl());


//        webView.loadData(detailHtml, "text/html", "UTF-8");
        webView.loadDataWithBaseURL(null,detailHtml, "text/html", "utf-8", null);




    }

//    String detailHtml = "<h1 class=\"ql-align-center\">昊欣后台技术部< img src=\"http://192.168.3.43:4041/hengyuaniot-party-building/party_branch_introduced_6239157232668672_6239157232668673.jpg\" width=\"159\" style=\"display: block; margin: auto;\"></h1>";
    String detailHtml = "<!DOCTYPE html>\n" +
        "<html>\n" +
        " <head>\n" +
        "  <meta charset=\"UTF-8\">\n" +
        "  <title>标题</title>\n" +
        " </head>\n" +
        " <style>\n" +
        " .cont{\n" +
        "\tmargin: 2% 2%;\n" +
        "\ttext-align: center;;\n" +
        " }\n" +
        " .cont p{\n" +
        "\ttext-align: left;;\n" +
        " }\n" +
        " </style>\n" +
        " <body>\n" +
        " <div class=\"cont\">\n" +
        "  <h1 class=\"ql-align-center\">昊欣后台技术部<img src=\"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn22%2F440%2Fw540h700%2F20180930%2F12ab-hktxqah1884198.png&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614766087&t=579a16c8fe392dbfff891456254c45fe\" width=\"159\" style=\"display: block; margin: auto;\"></h1>\n" +
        " </div>\n" +
        " </body>\n" +
        "</html>\n";

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
        return R.layout.activity_testside;
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
