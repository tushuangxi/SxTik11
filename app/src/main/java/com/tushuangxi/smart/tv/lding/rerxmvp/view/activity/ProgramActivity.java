package com.tushuangxi.smart.tv.lding.rerxmvp.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.billy.android.preloader.PreLoader;
import com.billy.android.preloader.PreLoaderWrapper;
import com.billy.android.preloader.interfaces.DataListener;
import com.billy.android.preloader.interfaces.DataLoader;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.rerxmvp.base.BaseActivity;

public class ProgramActivity  extends BaseActivity {

    //开启预加载
    private PreLoaderWrapper<String> preLoader;

    @Override
    public void initView() {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_program;
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
    public void initBundleData(Bundle bundle) {

    }

    @Override
    public void getHttpData(Context context) {
        initPreLoader(context);
    }

    private void initPreLoader(Context context) {
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

    @Override
    public void onWidgetClick(View view) {

    }
}
