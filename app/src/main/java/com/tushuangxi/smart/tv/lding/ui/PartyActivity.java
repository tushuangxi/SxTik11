package com.tushuangxi.smart.tv.lding.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.billy.android.preloader.PreLoader;
import com.billy.android.preloader.PreLoaderWrapper;
import com.billy.android.preloader.interfaces.DataListener;
import com.billy.android.preloader.interfaces.DataLoader;
import com.fengchen.uistatus.UiStatusController;
import com.fengchen.uistatus.annotation.UiStatus;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.rerxmvp.base.BaseActivity;
import com.tushuangxi.smart.tv.lding.utils.DateUtil;
import com.tushuangxi.smart.tv.library.nettyclient.netty.Config;
import com.tushuangxi.smart.tv.library.nettyclient.netty.EchoClient;
import com.tushuangxi.smart.tv.library.nettyclient.utils.LinkInfoUtil;
import com.tushuangxi.smart.tv.library.taskly.tasks.InitUmengTask;
import butterknife.BindView;
import io.netty.channel.ChannelHandlerContext;

public class PartyActivity extends BaseActivity {

    String TAG = "TAG: "+ PartyActivity.class.getSimpleName()+"....";
    PartyActivity mActivity;
    private UiStatusController mUiStatusController;
    private PreLoaderWrapper<String> preLoader;
    //netty---
    public static ChannelHandlerContext mChannelHandlerContext;

    @BindView(R.id.re_root)
    RelativeLayout re_root;
    @BindView(R.id.tv_init)
    TextView tv_init;

    @Override
    public void initBundleData(Bundle bundle) {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {
        mActivity = PartyActivity.this;
        mUiStatusController = UiStatusController.get().bind(findViewById(R.id.re_root));
        if (1==1){
            re_root.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mUiStatusController.changeUiStatusIgnore(UiStatus.CONTENT);
                }
            },2000);

        }else {
            mUiStatusController.changeUiStatusIgnore(UiStatus.EMPTY);
//            mUiStatusController.changeUiStatusIgnore(UiStatus.LOAD_ERROR);
        }


        //上传图片 报错  解决android.os.NetworkOnMainThreadException
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        addOnClickListeners(R.id.re_root
                , R.id.tv_init
         );

        //netty
        connNettyLink();

        //时间 天气
        setTimeUI();
        mHandler.postDelayed(runnable,  1*60*60);
    }

    private static Handler mHandler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            //时间 天气
            setTimeUI();
            mHandler.postDelayed(this, 1*60*60);
        }
    };

    private void setTimeUI() {
        tv_init.setText( DateUtil.formatSystemtime()+ "  "+DateUtil.getWeek());
        tv_init.setText(DateUtil.nowSystemtime());
        if (!TextUtils.isEmpty(InitUmengTask.temperature)){
            tv_init.setText(InitUmengTask.weather+"  "+InitUmengTask.temperature+"℃");
        }
    }

    @Override
    public void getHttpData(Context context) {
        initPreLoader(context);
    }

    public  void initPreLoader(Context context) {
        preLoader = PreLoader.just(new Loader(), new LoaderDataListener());
        preLoader.listenData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    protected void onResume() {
        super.onResume();

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


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_party;
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
        return true;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
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
    public boolean isSwipeEnable() {
        return false;
    }



    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {

            default:
        }
    }

    private void connNettyLink() {
        LinkInfoUtil.acquireLinkInfoFromProperties(mContext);
        String host = LinkInfoUtil.nettyAddrs;
        int port = LinkInfoUtil.nettyPort;
        conn(host, port);
    }


    public static void conn(final String host, final int port) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new EchoClient(host, port, new EchoClient.EchoCallBack() {
                    @Override
                    public void onConnSucess(ChannelHandlerContext type, String result, Object msg) {
                        mChannelHandlerContext=type;
                        Log.i("NettyParty", "onConnSucess " + result);
                    }

                    @Override
                    public void onReceive(String result) {
                        Log.i("NettyParty", "onReceive "+"\r\n" + result);
                    }

                    @Override
                    public void onExceptionTip(String result) {
                        Log.i("NettyParty", "onExceptionTip " + result);
                    }

                    @Override
                    public void onManualClose(String result) {
                        Log.i("NettyParty", "onManualClose " + result);
                    }

                    @Override
                    public void onReconn() {
                        Log.i("NettyParty", "onReconn--------------------------------------正在重连.. ");
                        conn(host, port);
                    }
                }).start();
            }
        }).start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (preLoader != null) {
            preLoader.destroy();
        }
        if (PartyActivity.mChannelHandlerContext != null) {
            PartyActivity.mChannelHandlerContext.channel().close();
            //不在重连
            Config.TCP_CONN_AGAIN = false;
            Log.i("NettyParty", "netty...已经关闭");
        } else {
            Log.i("NettyParty", "netty...未连接");
        }

    }

}
