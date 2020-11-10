package com.tushuangxi.smart.tv.lding.http;

import android.text.TextUtils;

import com.tao.admin.loglib.Logger;
import com.tushuangxi.smart.tv.lding.entity.SiteNavigationRsp;
import com.tushuangxi.smart.tv.lding.other.AppGlobalConsts;
import com.tushuangxi.smart.tv.lding.utils.JsonHandleUtils;
import com.tushuangxi.smart.tv.lding.utils.NetworkUtils;
import com.tushuangxi.smart.tv.lding.utils.SpfsUtils;
import com.tushuangxi.smart.tv.library.loading.conn.LoadingApp;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tushuangxi 2019.1.26
 */
public class RetrofitManager {
   static String TAG = "TAG: "+ RetrofitManager.class.getSimpleName()+"....";
    //连接超时时间 5s
    private static final long CONNECT_TIMEOUT_SECOND = 5;
    //缓存有效期 1天
    private static final long CACHE_STALE_SECOND = 24 * 60 * 60;
    //缓存大小 100M
    private static final long CACHE_SIZE = 1024 * 1024 * 100;

    private static RetrofitManager mRetrofitManager = null;
    private static OkHttpClient mOkHttpClient;
    //APIService
    private static ApiService apiService;

    public RetrofitManager(int hostType) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.getHost(hostType))//指定host
                .client(getOkHttpClient())//指定OKHttpClient
                .addConverterFactory(GsonConverterFactory.create())//指定转换器，不同的网络请求API规范可自定义转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        //创建APIService
        apiService = retrofit.create(ApiService.class);
    }

    // 配置OkHttpClient
    private static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    // OkHttpClient配置是一样的,静态创建一次即可
                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(LoadingApp.getContext().getCacheDir(), "HttpCache"),
                            CACHE_SIZE);

                    mOkHttpClient = new OkHttpClient.Builder().cache(cache)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(CONNECT_TIMEOUT_SECOND, TimeUnit.SECONDS).build();
                }
            }
        }
        return mOkHttpClient;
    }

        public static RetrofitManager getDefault(int hostType) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.getHost(hostType))
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

            //创建APIService
            apiService = retrofit.create(ApiService.class);

            synchronized (RetrofitManager.class) {
                //注意  每次都要new   不是单例
                mRetrofitManager = new RetrofitManager(hostType);
            }
        return mRetrofitManager;
    }

    /**
     * 根据网络状况获取缓存的策略
     */
    private String getCacheControl() {
        if (NetworkUtils.isConnected(LoadingApp.getContext())) {
            //网络畅通情况下，设置max-age=0，表示不读取缓存，直接去服务器请求最新的数据
            return "max-age=0";
        } else {
            //网络不畅通情况下，读取缓存，并设置缓存时间为CACHE_STALE_SECOND（1天）
            return "only-if-cached, max-stale=" + CACHE_STALE_SECOND;
        }
    }


    // server响应头拦截器，用来配置缓存策略
    private static Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isConnected(LoadingApp.getContext())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Logger.w(TAG,"no network");
//                TipUtil.newThreadToast("没有联网,请检查设备网络连接！");
            }
            Response originalResponse = chain.proceed(request);
            if (NetworkUtils.isConnected(LoadingApp.getContext())) {
                String token = SpfsUtils.readString(LoadingApp.getContext(), AppGlobalConsts.Token);
                if (TextUtils.isEmpty(token)) {
//                    Request originalRequest = chain.request();
//                    return chain.proceed(originalRequest);

                    String cacheControl = request.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .header("Content-Type", "application/json")
                            .removeHeader("Pragma").build();
                } else {
                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                    String cacheControl = request.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .addHeader("token", token)
                            .header("Cache-Control", cacheControl)
                            .header("Content-Type", "application/json")
                            .removeHeader("Pragma").build();
                }
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached," + CACHE_STALE_SECOND)
                        .removeHeader("Pragma").build();
            }

        }
    };

    // 打印json数据拦截器
    private static Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //request
            final Request request = chain.request();
            Logger.w(TAG,"-----------------------开始打印请求数据-----------------------");
            if (request != null) {
                Logger.w(TAG,"发送请求:"+ request.toString());
                Headers headers = request.headers();
                if (headers != null) {
                    Logger.w(TAG, "headers : " + headers.toString());
                }
                RequestBody body = request.body();
                if (body != null) {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    String req = buffer.readByteString().utf8();
                    Logger.w(TAG,"接收响应:"+ "body : " + req);
                }
            }
            Logger.w(TAG,"-----------------------结束打印请求数据-----------------------");

            //response
            final Response response = chain.proceed(request);
            final ResponseBody responseBody = response.body();
            final long contentLength = responseBody.contentLength();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException e) {
                    Logger.w(TAG,"Couldn't decode the response body; charset is likely malformed.");
                    return response;
                }
            }
            if (contentLength != 0) {
//                Logger.w(TAG, "-----------------------开始打印响应数据-----------------------");
                Logger.w(TAG,"响应数据: "+ buffer.clone().readString(charset));
//                Logger.w(TAG,"-----------------------结束打印响应数据-----------------------");
            }
            //retrofit   illegalStateException:closed     responseBody.string()必须注释掉 否则报错
            json = buffer.clone().readString(charset);
            responseString = ("JsonData--->拦截器："+ JsonHandleUtils.jsonHandle(buffer.clone().readString(charset)));
            Logger.w(TAG,responseString.toString()+"\n \n");
            return response;
        }
    };

    public static String json;
    public static String responseString;
    //请求------------------------------------------------------------------------------------------
    //  SiteNavigationRsp       //网站导航
    public  Observable<SiteNavigationRsp> getSiteNavigationRspObservable(RequestBody json) {
        return apiService.requestSiteNavigationRspList(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

}
