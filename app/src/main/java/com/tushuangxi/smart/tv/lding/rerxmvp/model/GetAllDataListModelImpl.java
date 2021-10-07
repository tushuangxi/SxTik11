package com.tushuangxi.smart.tv.lding.rerxmvp.model;

import android.content.Context;

import com.tushuangxi.smart.tv.lding.entity.SiteNavigationRsp;
import com.tushuangxi.smart.tv.lding.http.ApiConstants;
import com.tushuangxi.smart.tv.lding.http.HostType;
import com.tushuangxi.smart.tv.lding.http.RetrofitManager;
import com.tushuangxi.smart.tv.lding.rerxmvp.interfaceUtils.interfaceUtilsAll;
import com.tushuangxi.smart.tv.lding.widget.loading.LoadingDialog;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by tushuangxi 2019.1.26
 */
public  class GetAllDataListModelImpl implements interfaceUtilsAll.SubjectAllListModel {


    private LoadingDialog loadingDialog;

    public void startLoading(Context context) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
        }
        if (!loadingDialog.isShowing()){
            loadingDialog.show();
        }
    }

    public void stopLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    /**
     *   //  SiteNavigationRsp  网站导航
     * @param callback
     * @param json
     * @return
     */
    @Override
    public  Subscription requestSiteNavigationRspList(final interfaceUtilsAll.RequestCallback<SiteNavigationRsp> callback, RequestBody json, Context context) {
        return RetrofitManager.getAppDefault().getSiteNavigationRspObservable(json)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        callback.onBefore();
                        startLoading(context);
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SiteNavigationRsp>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        stopLoading();
                        callback.onError(e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SiteNavigationRsp siteNavigationRsp) {
                        stopLoading();
                        callback.onSuccess(siteNavigationRsp);
                    }
                });
    }

}
