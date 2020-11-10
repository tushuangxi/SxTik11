package com.tushuangxi.smart.tv.lding.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.tushuangxi.smart.tv.R;


/**
 * Created by
 *
 *      //方式一
 *   private LoadingDialogFg loadingDialog;
 *
 *     public void startLoading() {
 *         if (loadingDialog == null) {
 *             loadingDialog = new LoadingDialogFg(this);
 *         }
 *         if (!loadingDialog.isShowing())
 *             loadingDialog.show();
 *     }
 *
 *     public void stopLoading() {
 *         if (loadingDialog != null) {
 *             loadingDialog.dismiss();
 *             loadingDialog = null;
 *         }
 *     }
 *
 *     //方式二  单例

         public void startLoading() {
             loadingDialog = LoadingDialogFg.getInstance(context);
             if (!loadingDialog.isShowing())
                loadingDialog.show();
         }

         public void stopLoading() {
             if (loadingDialog != null) {
                 loadingDialog.dismiss();
                 loadingDialog = null;
             }
         }
 */
public class LoadingDialog extends Dialog {

    private static LoadingDialog mLoadingDialog = null;
    public LoadingDialog(Context context) {
        super(context, R.style.custom_loading_dialog);
    }

    public static LoadingDialog getInstance(Context context) {
        if (mLoadingDialog == null) {
            synchronized (LoadingDialog.class) {
                if (mLoadingDialog == null) {
                    mLoadingDialog = new LoadingDialog(context);
                }
            }
        }
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ncf_loading_layout);
        setCanceledOnTouchOutside(false);
    }
}
