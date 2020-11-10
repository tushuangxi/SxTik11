package com.tushuangxi.smart.tv.lding.widget.toptabbar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.eventbus.EventMessage;
import com.tushuangxi.smart.tv.lding.other.AppGlobalConsts;
import com.tushuangxi.smart.tv.lding.utils.SpfsUtils;
import com.tushuangxi.smart.tv.lding.utils.TipUtil;
import com.tushuangxi.smart.tv.library.loading.conn.LoadingApp;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.ButterKnife;

/**
 * Created by Carson_Ho on 16/7/22.
 * 组织设置
 */
public class Fragment4 extends Fragment {

    String TAG = "TAG: "+ Fragment4.class.getSimpleName()+"....";
    private Context mContext;
    TextView tv_title;

    public Fragment4(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);
        tv_title = view.findViewById(R.id.tv_title);

        String floatId = SpfsUtils.readString(LoadingApp.getContext(), AppGlobalConsts.FloatId);//悬浮活动id
        if (TextUtils.isEmpty(floatId)){
            showDialog();
        }else{
            TipUtil.showToast(LoadingApp.getContext(),"当前课程未结束",1000);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    private void showDialog() {
         new EditDialog(mContext).show();
    }

    //粘性事件  处理消息
    @Subscribe(threadMode= ThreadMode.MAIN, sticky=false)
    public void myEventBusMessage(EventMessage eventMessage){
        switch (eventMessage.getCode()) {

            default:
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //编辑对话框
    public class EditDialog extends Dialog implements View.OnClickListener {

        public EditDialog(@NonNull Context context) {
            super(context);
            initDilaog();
        }

        public EditDialog(@NonNull Context context, int themeResId) {
            super(context, themeResId);
            initDilaog();
        }

        protected EditDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
            initDilaog();
        }
        EditText tv_message;
        void initDilaog() {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.edit_dialog);
            setCancelable(false);
            setCanceledOnTouchOutside(false);

            String organizationName = SpfsUtils.readString(LoadingApp.getContext(), AppGlobalConsts.ORGANIZATION_NAME);
            String editOrganizationName = SpfsUtils.readString(LoadingApp.getContext(), AppGlobalConsts.EDIT_ORGANIZATION_NAME);
            tv_message = findViewById(R.id.tv_message);
            if (!TextUtils.isEmpty(editOrganizationName)){
                tv_message.setText(editOrganizationName);
            }else {
                tv_message.setText(organizationName);
            }
            findViewById(R.id.bt_close).setOnClickListener(this);
            findViewById(R.id.bt_commite).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bt_close:
                    dismiss();
                    break;

                case R.id.bt_commite:

                    break;
                    default:
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

}
