package com.tushuangxi.smart.tv.lding.rerxmvp.interfaceUtils;

import android.content.Context;
import com.tushuangxi.smart.tv.lding.entity.SiteNavigationRsp;
import okhttp3.RequestBody;
import rx.Subscription;

public class interfaceUtilsAll {

    public interface RequestCallback<T> {
        /**
         * 请求开始之前
         */
        void onBefore();

        /**
         * 请求完成
         */
        void onCompleted();

        /**
         * 请求成功
         */
        void onSuccess(T data);

        /**
         * 请求错误
         */
        void onError(Throwable throwable);
    }
    public interface IBaseView {

//        void showProgress();
//
//        void hideProgress();

    }

    //---------------------------------------------------------------------------------------------------------------所有的Model interface
    public interface SubjectAllListModel {
        Subscription requestSiteNavigationRspList(RequestCallback<SiteNavigationRsp> callback, RequestBody json, Context context);

    }

    //--------------------------------------------------------------------------------------------------------------- //网站导航  list   M V P
    public interface SiteNavigationRspView extends IBaseView {
        void updateSiteNavigationRspSuccess(SiteNavigationRsp siteNavigationRsp);
        void updateSiteNavigationRspError(Throwable throwable);
    }

    public interface SiteNavigationRspPresenter {
        void requestSiteNavigationRspList(int pageNum,int pageSize, Context context);
    }

}
