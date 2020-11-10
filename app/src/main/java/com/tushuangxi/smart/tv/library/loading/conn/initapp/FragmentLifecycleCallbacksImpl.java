package com.tushuangxi.smart.tv.library.loading.conn.initapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.tao.admin.loglib.Logger;
import com.tushuangxi.smart.tv.lding.utils.WeLog;


/**
 */
public class FragmentLifecycleCallbacksImpl extends FragmentManager.FragmentLifecycleCallbacks {

    String TAG = "TAG: "+ FragmentLifecycleCallbacksImpl.class.getSimpleName()+"....";
    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentCreated(fm, f, savedInstanceState);
        Logger.w(TAG,"onFragmentCreated: "+f.getClass().getSimpleName());

    }

    @Override
    public void onFragmentDetached(FragmentManager fm, Fragment f) {
        super.onFragmentDetached(fm, f);
        Logger.w("onFragmentDetached: "+f.getClass().getSimpleName());
    }
}
