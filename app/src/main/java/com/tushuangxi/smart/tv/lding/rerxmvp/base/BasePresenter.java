package com.tushuangxi.smart.tv.lding.rerxmvp.base;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.tushuangxi.smart.tv.lding.rerxmvp.interfaceUtils.interfaceUtilsAll;
import com.tushuangxi.smart.tv.lding.utils.NetworkUtils;
import com.tushuangxi.smart.tv.lding.widget.NoWorkDialog;
import com.tushuangxi.smart.tv.lding.widget.loading.LoadingDialog;
import com.tushuangxi.smart.tv.library.loading.conn.LoadingApp;
import com.vise.log.ViseLog;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;

/**
 *  desc  Presenter基类
 */
public abstract class BasePresenter<V extends interfaceUtilsAll.IBaseView, D>  implements interfaceUtilsAll.RequestCallback<D> {

    String TAG = "TAG: "+ BasePresenter.class.getSimpleName()+"....";
    LoadingDialog loadingDialog;
    protected Context context;

    protected Subscription mSubscription;
    protected V mView;

    public BasePresenter(V mView, Context context) {
        this.mView = mView;
        this.context = context;
    }
    public void startLoading() {
          if (loadingDialog == null) {
              loadingDialog = new LoadingDialog(context);
          }
          if (!loadingDialog.isShowing())
              loadingDialog.show();
      }

      public void stopLoading() {
          if (loadingDialog != null) {
              loadingDialog.dismiss();
              loadingDialog = null;
          }
     }

    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mView = null;
    }

    @Override
    public void onBefore() {
//        startLoading();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onSuccess(D data) {
//        stopLoading();
    }

    /**
     * onError统一在这里处理
     */
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
//        stopLoading();
        if (throwable instanceof HttpException ||throwable instanceof ConnectException||throwable instanceof SocketTimeoutException||throwable instanceof SocketTimeoutException||throwable instanceof ConnectTimeoutException) {
            HttpException httpException = (HttpException) throwable;
            int code = httpException.code();
            if (code == 500 || code == 404) {
//                TipUtil.newThreadToast("服务器错误");
            }
            if(context instanceof AppCompatActivity ){
                if (!NetworkUtils.isConnected(LoadingApp.getContext())) {
                    NoWorkDialog csdf = NoWorkDialog.getInstance();
                    FragmentTransaction ft = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                    csdf.show(ft, "NoWorkDialog");
                    ViseLog.w("onError... show   NoWorkDialog...:"+throwable.getMessage());
                }
            }
            ViseLog.w("onError...:"+throwable.getMessage());
        }
       /* else if (throwable instanceof ConnectException) {
//            TipUtil.newThreadToast("网络连接异常，请检查您的网络状态，稍后重试!");
        } else if (throwable instanceof SocketTimeoutException) {
//            TipUtil.newThreadToast("网络连接异常，请检查您的网络状态，稍后重试!");
        }
        else if (throwable instanceof ConnectTimeoutException) {
//            TipUtil.newThreadToast("网络连接异常，请检查您的网络状态，稍后重试!");
        } */else if (throwable instanceof UnknownHostException) {
//            TipUtil.newThreadToast("网络连接异常，请检查您的网络状态，稍后重试!");
        } else if (throwable instanceof NullPointerException) {
//            TipUtil.newThreadToast("空指针异常!");
        } else if (throwable instanceof javax.net.ssl.SSLHandshakeException) {
//            TipUtil.newThreadToast("证书验证失败!");
        } else if (throwable instanceof ClassCastException) {
//            TipUtil.newThreadToast("类型转换错误!");
        } else if (throwable instanceof IllegalStateException) {
//            TipUtil.newThreadToast("参数异常!");
        }else if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof JsonSyntaxException
                || throwable instanceof JsonSerializer
                || throwable instanceof NotSerializableException
                || throwable instanceof ParseException) {
//            TipUtil.newThreadToast("解析错误!");
        } else {
            //Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 1 path $
//            ToastUtil.showToast(AppProfile.context,"未知错误");
//            TipUtil.newThreadToast("未知错误!"+throwable.getMessage());
        }
    }
}