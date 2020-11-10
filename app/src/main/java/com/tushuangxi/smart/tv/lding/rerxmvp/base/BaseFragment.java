package com.tushuangxi.smart.tv.lding.rerxmvp.base;

/**
 *  desc  fragment基类
 */
public abstract class BaseFragment extends BaseAppFragment {


    /**
     * 加载普通布局 Layout
     * */
    @Override
    protected abstract int getContentViewLayoutId();


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
