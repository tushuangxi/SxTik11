package com.tushuangxi.smart.tv.lding.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.billy.android.preloader.PreLoader;
import com.billy.android.preloader.PreLoaderWrapper;
import com.billy.android.preloader.interfaces.DataListener;
import com.billy.android.preloader.interfaces.DataLoader;
import com.fengchen.uistatus.UiStatusController;
import com.fengchen.uistatus.annotation.UiStatus;
import com.tao.admin.loglib.Logger;
import com.tushuangxi.smart.tv.lding.entity.SiteNavigationRsp;
import com.tushuangxi.smart.tv.lding.eventbus.EventMessage;
import com.tushuangxi.smart.tv.lding.rerxmvp.base.BaseActivity;
import com.tushuangxi.smart.tv.lding.rerxmvp.interfaceUtils.interfaceUtilsAll;
import com.tushuangxi.smart.tv.lding.rerxmvp.presenter.SiteNavigationRspPresenter;
import com.tushuangxi.smart.tv.lding.widget.LoadingDialogFg;
import com.tushuangxi.smart.tv.library.updater.ui.UpdateVersionShowDialog;
import com.tushuangxi.smart.tv.library.updater.utils.AppUtils;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.utils.ActivityUtils;
import com.tushuangxi.smart.tv.lding.utils.DoubleClickHelper;
import com.tushuangxi.smart.tv.lding.utils.HideUtil;
import com.tushuangxi.smart.tv.lding.utils.TipUtil;
import com.tushuangxi.smart.tv.library.router.UiPage;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;

import butterknife.BindView;


public class InitActivity extends BaseActivity implements   interfaceUtilsAll.SiteNavigationRspView {

    String TAG = "TAG: "+ InitActivity.class.getSimpleName()+"....";
    public static  InitActivity mActivity;
    private UiStatusController mUiStatusController;
    private PreLoaderWrapper<String> preLoader;

    @BindView(R.id.ll_init_root)
    RelativeLayout ll_init_root;
    @BindView(R.id.bt_joinAuthor)
    Button bt_joinAuthor;

    @Override
    public void initBundleData(Bundle bundle) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        HideUtil.initHide(mActivity);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {
        mActivity = InitActivity.this;
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        //报错  解决android.os.NetworkOnMainThreadException
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mUiStatusController = UiStatusController.get().bind(findViewById(R.id.ll_init_root));
        if (1==1){
            mUiStatusController.changeUiStatusIgnore(UiStatus.CONTENT);
        }else {
            mUiStatusController.changeUiStatusIgnore(UiStatus.EMPTY);
//            mUiStatusController.changeUiStatusIgnore(UiStatus.LOAD_ERROR);
        }

        addOnClickListeners(R.id.ll_init_root
                , R.id.bt_joinAuthor
        );

    }

    @Override
    public void getHttpData(Context context) {
        initPreLoader(context);
    }

    private void initPreLoader(Context context) {
        preLoader = PreLoader.just(new Loader(), new LoaderDataListener());
        preLoader.listenData();
    }

    @Override
    public void updateSiteNavigationRspSuccess(SiteNavigationRsp siteNavigationRsp) {

    }

    @Override
    public void updateSiteNavigationRspError(Throwable throwable) {

    }


    class Loader implements DataLoader<String> {
        @Override
        public String loadData() {

            return null;
        }
    }

    class LoaderDataListener implements DataListener<String> {
        @SuppressLint("NewApi")
        @Override
        public void onDataArrived(String data) {
            initQueryText();
        }
    }

    //
    private void initQueryText() {

    }

    private void requestPermissionsMore() {
        XXPermissions.with(this)
                // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                .constantRequest()
                // 申请悬浮窗权限
                .permission(Permission.SYSTEM_ALERT_WINDOW)
                .permission(Permission.SYSTEM_ALERT_WINDOW)
                // 申请单个权限
//                .permission(Permission.CAMERA)
                // 申请多个权限
                .permission(Permission.Group.STORAGE)
                .permission(Permission.Group.LOCATION)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean all) {
                        hasAll = all;
                        if (all) {
                            //版本更新
                            getApkVersionUpdate(AppUtils.getVersionCode(mContext));
                        } else {
                            TipUtil.newThreadToast(R.string.not_granted_permission);
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        noQuick=quick;
                        if (quick) {
                            TipUtil.newThreadToast(R.string.denied_authorization);
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(mContext, denied);
                        } else {
                            TipUtil.newThreadToast(R.string.failed_to_get_permission);
                        }
                    }
                });
    }

    private boolean hasAll,noQuick;

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

    //粘性事件  处理消息
    @Subscribe(threadMode= ThreadMode.MAIN, sticky=false)
    public void myEventBusMessage(EventMessage eventMessage){
        switch (eventMessage.getCode()) {

            default:
        }
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
    protected int getContentViewLayoutId() {
        return R.layout.activity_init;
    }

    @Override
    protected View getContentViewLayoutView() {
        return null;
    }

    @Override
    public void onBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick()) {
            //移动到上一个任务栈，避免侧滑引起的不良反应
            moveTaskToBack(false);
            ll_init_root.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityUtils.finishAllActivity();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }, 300);
        } else {
            TipUtil.newThreadToast(R.string.again_to_exit);
        }
    }

    //接入按钮  3s内只可响应点击一次
    private boolean isoncl=true;
    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.ll_init_root:
//                if (hasAll){
//                    UiPage.init(mContext).with(mActivity, PartyActivity.class,false);
//                }else if (noQuick){
//                    TipUtil.showToast(mContext,R.string.denied_authorization, 1000);
//                    requestPermissionsMore();
//                }else {
//                    TipUtil.showToast(mContext,R.string.not_granted_permission, 1000);
//                }
                break;

            case R.id.bt_joinAuthor:
                if(isoncl){
                    isoncl=false;
                    //点击3s后就改成true，这样zhi就实现只dao点击一次了
                    ll_init_root.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isoncl=true;
                        }
                    },3000);

                    UiPage.init(mContext).with(mActivity, PartyActivity.class,false);
                }
                break;

            default:
        }
    }

    private LoadingDialogFg loadingDialogFg;

    //显示等待框
    public void showLoadingfg(String msg,boolean touch){
        if(loadingDialogFg == null){
            loadingDialogFg = new LoadingDialogFg();
        }else{
            loadingDialogFg.dismiss();
        }
        loadingDialogFg.setMsg(msg)
                .setOnTouchOutside(touch)
                .show(getSupportFragmentManager(),"loading");
        //fragment的话就把getSupportFragmentManager参数换成getChildFragmentManager
    }

    //动态修改等待框中的文字
    public void setLoadingMsg(String msg){
        if(loadingDialogFg == null){
            return;
        }
        loadingDialogFg.setMsg(msg);
    }

    //隐藏等待框
    public void hideLoadingfg(){
        if(loadingDialogFg != null){
            loadingDialogFg.dismiss();
        }
    }

    //----------------------------------------------------------------------------------------------
    private void goContinueActivity(String deviceIdentity) {
        SiteNavigationRspPresenter.getPresenter(this,mContext).requestSiteNavigationRspList( deviceIdentity,this);
    }

    public static void getApkVersionUpdate(int versionNumber) {
//        ApkVersionUpdateJsonData jsonData = new ApkVersionUpdateJsonData();
//        jsonData.setVersionNumber(versionNumber);
//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(jsonData));
//        RetrofitManager.getDefault(HostType.TYPE_HOST_HENGYUANIOT).getApkVersionUpdateRspObservable(body)
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//
//                    }
//                })
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ApkVersionUpdateRsp>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(ApkVersionUpdateRsp apkVersionUpdateRsp) {
//                        Logger.w("TAG","ApkVersionUpdateRsp:"+apkVersionUpdateRsp.getMessage());
//                        if (apkVersionUpdateRsp.getCode()==AppGlobalConsts.HTTP_SUCCESS){
//                            if (apkVersionUpdateRsp.getResult()==null){
//                                return;
//                            }
//                            int versionNumber = apkVersionUpdateRsp.getResult().getVersionNumber();
//                            String url = apkVersionUpdateRsp.getResult().getUrl();
//                            //拼接
//                            String apkUrl = ApiConstants.HOST_HENGYUANIOT + url;
//                            if (!TextUtils.isEmpty(apkUrl)) {
//                                updateCheck(versionNumber, apkUrl);
//                            }
//                        }
//                    }
//                });
    }

    public static UpdateVersionShowDialog dialog;
    public static void updateCheck(int versionNumber, String apkUrl) {
        if(versionNumber > AppUtils.getVersionCode(mActivity)){
            //发现新版本
            dialog = UpdateVersionShowDialog.show(mActivity, apkUrl);
        }
    }

    @Override
    public void onNetworkStateChanged(boolean networkConnected, NetworkInfo currentNetwork, NetworkInfo lastNetwork) {
        if(networkConnected) {
//            Logger.w(TAG,"网络状态:" + (null == currentNetwork ? "" : ""+currentNetwork.getTypeName()+":"+currentNetwork.getState()));
//            TipUtil.newThreadToast("网络已连接!");
            //版本更新
            if (dialog!=null&&dialog.commitUpdater){
                dialog.goUpdater("网络已连接,请稍后...");
                Logger.w(TAG,"goUpdater...");
            }
        } else {
//            TipUtil.newThreadToast("网络已断开!");
        }
//        Logger.w(TAG,null == currentNetwork ? "网络状态:无网络连接" : "网络状态:"+currentNetwork.toString());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (preLoader != null) {
            preLoader.destroy();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
      if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                exit();
                ActivityUtils.finishAllActivity();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void exit() {
        if (preLoader != null) {
            preLoader.destroy();
        }
    }
}
