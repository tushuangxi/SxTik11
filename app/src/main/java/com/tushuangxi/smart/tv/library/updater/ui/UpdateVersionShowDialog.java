package com.tushuangxi.smart.tv.library.updater.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.eventbus.EventCode;
import com.tushuangxi.smart.tv.lding.eventbus.EventMessage;
import com.tushuangxi.smart.tv.lding.utils.NetworkUtils;
import com.tushuangxi.smart.tv.library.loading.conn.LoadingApp;
import com.tushuangxi.smart.tv.library.updater.AppUpdater;
import com.tushuangxi.smart.tv.library.updater.INetDownCallBack;
import com.tushuangxi.smart.tv.library.updater.entity.ApkInfo;
import com.tushuangxi.smart.tv.library.updater.utils.AppUtils;
import org.greenrobot.eventbus.EventBus;
import java.io.File;

/**
 **/
public class UpdateVersionShowDialog extends DialogFragment {
    public static final String KEY_DOWNLOAD_BEAN = "download_bean";

    private ApkInfo apkInfo;
    private String apkUrl;

    LinearLayout ll_updater_1,ll_updater_2;
    TextView tv_title_1,tv_title_2,tv_content,tv_update,progesss_value;
    ProgressBar numberProgressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments != null){
//            apkInfo = (ApkInfo) arguments.getSerializable(KEY_DOWNLOAD_BEAN);
            apkUrl = arguments.getString(KEY_DOWNLOAD_BEAN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_updater, container, false);
        bindEvents(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void bindEvents(View view){
        ll_updater_1 = view.findViewById(R.id.ll_updater_1);
        ll_updater_2 = view.findViewById(R.id.ll_updater_2);
        tv_title_1 = view.findViewById(R.id.tv_title_1);

        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_commite = view.findViewById(R.id.tv_commite);

        tv_title_2 = view.findViewById(R.id.tv_title_2);
        tv_content = view.findViewById(R.id.tv_content);
        tv_update = view.findViewById(R.id.tv_update);
        progesss_value = view.findViewById(R.id.progesss_value);
        numberProgressBar = view.findViewById(R.id.numberProgressBar);

        ll_updater_1.setVisibility(View.VISIBLE);
        ll_updater_2.setVisibility(View.GONE);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitUpdater = false;
                dismiss();
//                EventBus.getDefault().postSticky(new EventMessage(EventCode.SYN_CODE_CLOSE_CHECK_WORK_DATA));
            }
        });

        tv_commite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitUpdater = true;
                if (!NetworkUtils.isConnected(LoadingApp.getContext())) {
                    goUpdaterError("下载失败,请检查网络...");
                }else {
                    goUpdater("正在下载,请稍后...");
                }

            }
        });
    }

    private void goUpdaterError(String meaasge) {
        ll_updater_1.setVisibility(View.GONE);
        ll_updater_2.setVisibility(View.VISIBLE);
        tv_title_2.setText(meaasge);
    }

    public  boolean commitUpdater = false;
    public void goUpdater(String meaasge) {
        ll_updater_1.setVisibility(View.GONE);
        ll_updater_2.setVisibility(View.VISIBLE);
        tv_title_2.setText(meaasge);
        numberProgressBar.setProgress(0);

        //自动下载
        File file = new File(getContext().getCacheDir(),"targetFile.apk");
        System.out.println(getContext().getCacheDir());
        AppUpdater.getInstance().getNetManager().downLoad(apkUrl, file, new INetDownCallBack() {
            @Override
            public void success(File targetFile) {
                dismiss();
                AppUtils.installApk(getContext(),targetFile);
                System.out.println("下载完成"+targetFile.getAbsolutePath());
            }

            @Override
            public void progress(int progress) {
                numberProgressBar.setProgress(progress);
//                progesss_value.setText(new StringBuffer().append(numberProgressBar.getProgress()).append("%"));
                progesss_value.setText(new StringBuffer().append(progress).append("%"));
//                tv_update.setText(progress + "%");
                System.out.println("下载进度："+progress);
            }

            @Override
            public void failed(Throwable throwable) {
//                tv_update.setClickable(true);
                Looper.prepare();
//                Toast.makeText(LoadingApp.getContext(), "下载失败,网络异常", Toast.LENGTH_SHORT).show();
                tv_title_2.setText("下载失败,请检查网络...  ");
                Looper.loop();
            }
        });
    }

//    public static void show(FragmentActivity activity, ApkInfo apkInfo){
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(KEY_DOWNLOAD_BEAN,apkInfo);
//        UpdateVersionShowDialog dialog = new UpdateVersionShowDialog();
//        dialog.setArguments(bundle);
//        dialog.show(activity.getSupportFragmentManager(),"updateVersionShowDialog");
//    }

    public static UpdateVersionShowDialog dialog;
    public static UpdateVersionShowDialog show(FragmentActivity activity, String apkUrl){
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DOWNLOAD_BEAN,apkUrl);
        if (dialog!=null){
            dialog.dismiss();
        }
        dialog = new UpdateVersionShowDialog();
        dialog.setCancelable(false);
        dialog.setArguments(bundle);
        dialog.show(activity.getSupportFragmentManager(),"updateVersionShowDialog");
        return dialog;
    }

}
