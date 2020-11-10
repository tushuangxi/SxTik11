package com.tushuangxi.smart.tv.lding.rerxmvp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.tushuangxi.smart.tv.lding.other.AppGlobalConsts;
import com.tushuangxi.smart.tv.lding.utils.HideUtil;

import org.greenrobot.eventbus.EventBus;
import butterknife.ButterKnife;

public abstract class BaseAppFragment extends Fragment implements ActBaseView, View.OnClickListener {

    protected Context mContext = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }

    }

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getContentViewLayoutId()!= AppGlobalConsts.GLOBAL_ZERO){
            //加载布局资源id
            view = inflater.inflate(getContentViewLayoutId(), container, false);
        }else{
            //加载templateUI布局
            return super.onCreateView(inflater, container, savedInstanceState);
//            view = inflater.inflate(getContentViewLayoutView(), container, false);
        }

        if (view instanceof ViewGroup) {
            HideUtil.init(getActivity(), (ViewGroup) view);//初始化触摸关闭软键盘
        }
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 加载普通布局 Layout
     * */
    protected abstract int getContentViewLayoutId();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        initBundleData(bundle);
        initView();
    }


    /**
     * 上次点击时间
     */
    private long lastClick = 0;
    /**
     * 判断是否快速点击
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

    @Override
    public void onClick(final View view) {
        if (!isFastClick()){
            onWidgetClick(view);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract boolean isBindEventBusHere();


}

