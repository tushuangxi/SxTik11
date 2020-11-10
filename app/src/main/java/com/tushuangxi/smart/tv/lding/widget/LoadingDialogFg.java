package com.tushuangxi.smart.tv.lding.widget;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tushuangxi.smart.tv.R;

import java.util.Objects;

/**
 * 等待弹框
 */
public class LoadingDialogFg extends DialogFragment {

    private String msg = "正在加载";
    private boolean onTouchOutside = true;
    private TextView textView;
    public static View loadingView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(onTouchOutside);

        loadingView = inflater.inflate(R.layout.dialog_loading, container);
        textView= loadingView.findViewById(R.id.tv_message);
        textView.setText(msg);
        return loadingView;
    }

    public LoadingDialogFg setMsg(String msg) {
        this.msg = msg;
        if (textView!= null) {
            textView.setText(msg);
        }
        return this;
    }

    public LoadingDialogFg setOnTouchOutside(boolean onTouchOutside) {
        this.onTouchOutside = onTouchOutside;
        if(getDialog() != null){
            getDialog().setCanceledOnTouchOutside(onTouchOutside);
        }
        return this;
    }
}