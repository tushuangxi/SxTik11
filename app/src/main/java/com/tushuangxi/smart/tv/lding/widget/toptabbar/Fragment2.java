package com.tushuangxi.smart.tv.lding.widget.toptabbar;

import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.billy.android.preloader.PreLoaderWrapper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fengchen.uistatus.UiStatusController;
import com.fengchen.uistatus.annotation.UiStatus;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.http.ApiConstants;
import com.tushuangxi.smart.tv.lding.other.AppGlobalConsts;
import com.tushuangxi.smart.tv.lding.utils.SpfsUtils;
import com.tushuangxi.smart.tv.lding.widget.Html5WebView;
import com.tushuangxi.smart.tv.library.loading.conn.LoadingApp;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;


/**
 * Created by Carson_Ho on 16/7/22.
 *
 * 使用教程
 */
public class Fragment2 extends Fragment  {

    String TAG = "TAG: "+ Fragment2.class.getSimpleName()+"....";
    private ImageView tv_pptPlay;
    private StandardGSYVideoPlayer mGsy;
    private boolean isPlay;

    //
    private UiStatusController mUiStatusController;
    private PreLoaderWrapper<String> preLoader;

    LinearLayout ll_web_view;
    FrameLayout web_layout;
    Html5WebView webView;
    ProgressBar progressbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        //--------------------------------------------------------------------------------------- //video播放
        tv_pptPlay = view.findViewById(R.id.tv_pptPlay);
        mGsy = view.findViewById(R.id.gsy_player);
        String videoUrl = SpfsUtils.readString(LoadingApp.getContext(), AppGlobalConsts.VIDE_OURL);
        mGsy.setUp(ApiConstants.HOST_HENGYUANIOT+videoUrl, true, "");

        // 指定时间进度播放（至少3位数）
//        mGsy.setSeekOnStart(60123);
        isPlay = true;
        //增加封面1
        ImageView imageView = new ImageView(LoadingApp.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.mipmap.bg_dangyuanbg_2x);
        mGsy.setThumbImageView(imageView);

//        Glide.with(LoadingApp.getContext()).load("http://a4.att.hudong.com/05/71/01300000057455120185716259013.jpg").into(imageView);

        //增加封面2
        loadCover(imageView,ApiConstants.HOST_HENGYUANIOT+videoUrl,LoadingApp.getContext());

        //增加title
        mGsy.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        mGsy.getBackButton().setVisibility(View.GONE);
        //设置全屏按键功能
        mGsy.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 第一个是否需要隐藏actionBar，第二个是否需要隐藏statusBar
                mGsy.startWindowFullscreen(getActivity(), true, true);
            }
        });
        //是否可以滑动调整
        mGsy.setIsTouchWiget(true);

        //---------------------------------------------------------------------------------------//webView展示
        ll_web_view = view.findViewById(R.id.ll_web_view);
        web_layout = view.findViewById(R.id.web_layout);
        webView = view.findViewById(R.id.webView);
        progressbar = view.findViewById(R.id.progressbar);
        mUiStatusController = UiStatusController.get().bind(view.findViewById(R.id.web_layout));
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
        webView.setWebViewClient(new GankClient());
        webView.loadUrl("https://www.baidu.com/");
        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            getCurPlay().release();
        }

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
    private GSYVideoPlayer getCurPlay() {
        if (mGsy.getFullWindowPlayer() != null) {
            return mGsy.getFullWindowPlayer();
        }
        return mGsy;
    }

    /**
     * 加载第四秒的帧数作为封面
     *  url就是视频的地址
     */
    public static void loadCover(ImageView imageView, String url, Context context) {

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(3000000)
                                .centerCrop()
//                                .error(R.mipmap.eeeee)//可以忽略
//                                .placeholder(R.mipmap.ppppp)//可以忽略
                )
                .load(url)
                .into(imageView);
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

     class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            super.onProgressChanged(webView, newProgress);
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

        class GankClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (url.startsWith("xhsdnative:")) {
                return true;
            }
            if (url != null) {
                webView.loadUrl(url);
            }

            return true;
        }
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
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
