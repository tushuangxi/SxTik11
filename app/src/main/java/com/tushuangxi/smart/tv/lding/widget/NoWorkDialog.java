package com.tushuangxi.smart.tv.lding.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.fragment.app.DialogFragment;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.utils.ActivityUtils;

public class NoWorkDialog  extends DialogFragment implements View.OnClickListener {

    String TAG = "TAG: "+ NoWorkDialog.class.getSimpleName()+"....";

    //写一个静态方法产生实例
    private volatile static NoWorkDialog mInstance;

    public static NoWorkDialog getInstance() {
        if (mInstance == null) {
            synchronized (NoWorkDialog.class) {
                if (mInstance == null) {
                    mInstance = new NoWorkDialog();
                }
            }
        }
        return mInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nowork_dialog, container, false);
        ImageView iv_close =  v.findViewById(R.id.iv_close);
        Button btn_commit = v.findViewById(R.id.btn_commit);
        iv_close.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { //此处可以设置Dialog的style等等
        super.onCreate(savedInstanceState);
        setCancelable(false);//无法直接点击外部取消dialog
        setStyle(DialogFragment.STYLE_NO_FRAME,0); //NO_FRAME就是dialog无边框，0指的是默认系统Theme
    }

    @Override
    public void onClick(View v) {  //点击事件
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.btn_commit:
                dismiss();

                //lding.ui.InitActivity  如果是首页 就刷新请求。
                String localClassName = ActivityUtils.currentActivity().getLocalClassName();
                String  subClassName=localClassName.substring(localClassName.length()-12);
                if (subClassName.equals("InitActivity")){
                    //InitActivity 页面 刷新数据  版本更新
//                    EventBus.getDefault().postSticky(new EventMessage(EventCode.SYN_CODE_CLOSE_REFURBISH_DATA));
                }
                break;
            default:
                break;
        }
    }
}