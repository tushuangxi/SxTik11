package com.tushuangxi.smart.tv.lding.rerxmvp.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.tushuangxi.smart.tv.lding.entity.SiteNavigationRsp;
import com.tushuangxi.smart.tv.lding.entity.request.CheckActivityStatusJsonData;
import com.tushuangxi.smart.tv.lding.rerxmvp.base.BasePresenter;
import com.tushuangxi.smart.tv.lding.rerxmvp.interfaceUtils.interfaceUtilsAll;
import com.tushuangxi.smart.tv.lding.rerxmvp.model.GetAllDataListModelImpl;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by tushuangxi 2019.1.26
 */
public class SiteNavigationRspPresenter extends BasePresenter<interfaceUtilsAll.SiteNavigationRspView, SiteNavigationRsp> implements interfaceUtilsAll.SiteNavigationRspPresenter{

    private GetAllDataListModelImpl iSiteNavigationRspInteractor;
    private volatile static SiteNavigationRspPresenter mInstance;

    public static SiteNavigationRspPresenter getPresenter(interfaceUtilsAll.SiteNavigationRspView mView, Context context)  {
        mInstance = new SiteNavigationRspPresenter(mView,context);
        return mInstance;
    }

    public SiteNavigationRspPresenter(interfaceUtilsAll.SiteNavigationRspView mView, Context context) {
        super(mView, context);
        this.context = context;
        iSiteNavigationRspInteractor = new GetAllDataListModelImpl();
    }
    @Override
    public void requestSiteNavigationRspList(String deviceIdentity, Context context) {
        CheckActivityStatusJsonData jsonData = new CheckActivityStatusJsonData();
//        jsonData.setDeviceIdentity(deviceIdentity);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(jsonData));
        mSubscription = iSiteNavigationRspInteractor.requestSiteNavigationRspList(this,body,context);
    }

    @Override
    public void onSuccess(SiteNavigationRsp siteNavigationRsp ){
        super.onSuccess(siteNavigationRsp);
        mView.updateSiteNavigationRspSuccess(siteNavigationRsp);
    }

    @Override
    public void onError(Throwable throwable){
        super.onError(throwable);
        mView.updateSiteNavigationRspError(throwable);
    }
}
