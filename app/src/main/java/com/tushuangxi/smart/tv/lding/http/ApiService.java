package com.tushuangxi.smart.tv.lding.http;

import com.tushuangxi.smart.tv.lding.entity.SiteNavigationRsp;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by tushuangxi 2020.8.11
 */
public interface ApiService {
    /**
     * SiteNavigationRsp  //网站导航
     */
    @POST("externalLink/list")
    Observable<SiteNavigationRsp> requestSiteNavigationRspList(@Body RequestBody json);

}


