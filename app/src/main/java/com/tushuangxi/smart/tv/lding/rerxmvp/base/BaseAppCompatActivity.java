package com.tushuangxi.smart.tv.lding.rerxmvp.base;

import android.app.Activity;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.tao.admin.loglib.Logger;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.eventbus.EventMessage;
import com.tushuangxi.smart.tv.lding.other.AppGlobalConsts;
import com.tushuangxi.smart.tv.lding.rerxmvp.service.navigationview.AbsMeanager;
import com.tushuangxi.smart.tv.lding.utils.StatusBarCompat;
import com.tushuangxi.smart.tv.lding.utils.network.NetworkManager;
import com.tushuangxi.smart.tv.lding.utils.network.NetworkObserver;
import com.tushuangxi.smart.tv.lding.widget.NoWorkDialog;
import com.tushuangxi.smart.tv.library.loading.conn.PreTaskManager;
import com.lky.toucheffectsmodule.factory.TouchEffectsFactory;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.ButterKnife;

public abstract class BaseAppCompatActivity extends AppCompatActivity implements ActBaseView, View.OnClickListener, PreTaskManager.SwipeAction ,NetworkObserver{

    String TAG = BaseAppCompatActivity.class.getSimpleName()+"....";
    protected Context mContext;

    public enum TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionType()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in,R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in,R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in,R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in,R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in,R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    break;
                default:
            }
        }
        //触摸特效  注意此功能 与 AlertDialog 冲突。可以使用最定义dialog 替换
        touchEffectsView();
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        initBundleData(bundle);
        doBeforeSetcontentView();
        if(getContentViewLayoutId()!= AppGlobalConsts.GLOBAL_ZERO){
            //加载布局资源id
            setContentView(getContentViewLayoutId());
        }else{
            //加载templateUI布局
            setContentView(getContentViewLayoutView());
        }
        mContext=this;
        //去掉标题栏  1. 继承android.support.v7.app.AppCompatActivity   使用 if(){ getSupportActionBar().hide(); } 隐藏标题栏
        // 2.继承android.app.Activity或者android.support.v4.app.FragmentActivity  使用 requestWindowFeature(Window.FEATURE_NO_TITLE);隐藏标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);

        initView();
        getHttpData(mContext);

        if(isBindEventBus()){
            EventBus.getDefault().register(this);
        }

        if (isResultOK()){
            setResult(Activity.RESULT_OK, null);
        }

        addOnClickListeners();
        setTranslucentStatus(isApplyStatusBarTranslucency());
        changeStatusBarTextColor(true);

        //网络状态 观察者
        NetworkManager.getInstance().initialized(this);
        NetworkManager.getInstance().registerNetworkObserver(this);
    }

    @Override
    public void onNetworkStateChanged(boolean networkConnected, NetworkInfo currentNetwork, NetworkInfo lastNetwork) {
        if(networkConnected) {
//            Logger.w(TAG,"网络状态:" + (null == currentNetwork ? "" : ""+currentNetwork.getTypeName()+":"+currentNetwork.getState()));
//            TipUtil.newThreadToast("网络已连接!");
        } else {
//            TipUtil.newThreadToast("网络已断开!");
            NoWorkDialog csdf = NoWorkDialog.getInstance();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            csdf.show(ft, "NoWorkDialog");
        }
//        Logger.w(TAG,null == currentNetwork ? "网络状态:无网络连接" : "网络状态:"+currentNetwork.toString());
    }

    protected  void touchEffectsView(){
        //缩放效果示例
        TouchEffectsFactory.initTouchEffects(this,TouchEffectsWholeType.SCALE);
        //水波纹效果示例
//        TouchEffectsFactory.initTouchEffects(this, TouchEffectsWholeType.RIPPLE);
        //水波纹1效果示例
//        TouchEffectsFactory.initTouchEffects(this,TouchEffectsWholeType.RIPPLE_1);
        //渐变效果示例
//        TouchEffectsFactory.initTouchEffects(this,TouchEffectsWholeType.STATE);
    };

    protected void addOnClickListeners(@IdRes int... ids) {
        if (ids != null) {
            View viewId;
            for (int id : ids) {
                viewId = findViewById(id);
                if (viewId != null) {
                    viewId.setOnClickListener(this);
                }
            }
        }
    }

    /**
     * 加载普通布局 Layout
     * */
    protected abstract int getContentViewLayoutId();

    /**
     * 加载templateUI布局 Layout
     * */
    protected abstract View getContentViewLayoutView();


    @Override
    protected void onStart() {
        if (isOpenFloatingAnimationService()) {
            AbsMeanager.getInstance().startFloatingAnimation();
        }else {
            AbsMeanager.getInstance().stopFloatingAnimation();
        }

        if (isOpenFloatingErWerMaService()) {
            AbsMeanager.getInstance().startErWerMaFloat();
        }else {
            AbsMeanager.getInstance().stopErWerMaFloat();
        }
        super.onStart();

    }


    @Override
    protected void onRestart() {
        super.onRestart();

    }

    //粘性事件  处理消息
    @Subscribe(threadMode= ThreadMode.MAIN, sticky=false)
    public void EventBusMessage(EventMessage eventMessage){
        switch (eventMessage.getCode()) {
//            case EventCode.SYN_CODE_CLOSE_DATA://
//
//                break;
//
//            case EventCode.SYN_CODE_CLOSE_CHECK_WORK_DATA://
//                if (!NetworkUtils.isConnected(LoadingApp.getContext())) {
//                    NoWorkDialog csdf = NoWorkDialog.getInstance();
//                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                    csdf.show(ft, "NoWorkDialog");
//                }else {
//                    onResume();
//                }

            default:
        }
    }



    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

    }
    @Override
    public void setContentView(View layoutResView) {
        super.setContentView(layoutResView);
        ButterKnife.bind(this);

    }
    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        //保持竖屏
//        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
//        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.role_yellow_gray));

//        XStatusBar.setColor(this, getResources().getColor(R.color.colorPrimary),0);
    }
    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 上次点击时间
     */
    private long lastClick = 0;
    /**
     * 判断是否快速点击
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 1000) {
            lastClick = now;
            return false;
        }
        return true;
    }

    @Override
    public void onClick(final View view) {
        if (!isFastClick()){
            onWidgetClick(view);
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionType()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in,R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in,R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in,R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in,R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in,R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBindEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
        透明状态栏
     */
    protected void setTranslucentStatus(boolean on) {
        if (on) {
            // 默认着色状态栏
            SetStatusBarColor();
        }
    }
    /**
     * 改变状态栏字体颜色值
     *
     * @param isBlack
     */
    private void changeStatusBarTextColor(boolean isBlack) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }
    }

    /**
     * activity进入退出是否需要动画设置
     * */
    protected abstract boolean toggleOverridePendingTransition();

    /**
     * activity动画选择器
     * */
    protected abstract TransitionMode getOverridePendingTransitionType();

    /**
     * 界面是否注册监听器
     *
     *  // 注意 如果Activity的  isBindEventBus  true注册了  当前Activity就要带上这个方法  否则报错
     *  Caused by: de.greenrobot.event.EventBusException: Subscriber cl    ProgramActivity and its super classes have no public methods with the @Subscribe annotation
     *      //粘性事件  处理消息
     *     @Subscribe(threadMode= ThreadMode.MAIN, sticky=true)
     *     public void myEventBusMessage(EventClientMessage eventMessage){
     *
     *     }
     * */
    protected abstract boolean isBindEventBus();

    /**
     * 是否将导航栏透明化
     * */
    protected abstract boolean isApplyStatusBarTranslucency();

    /**
     * 是否返回数据   setResult  将 Result 设置为Ok  -1
     * */
    protected abstract boolean isResultOK();
    /**
     * 是否启动悬浮 导航栏
     * */
    protected abstract boolean isOpenFloatingAnimationService();
    /**
     * 是否启动 悬浮二维码
     * */
    protected abstract boolean isOpenFloatingErWerMaService();


}
