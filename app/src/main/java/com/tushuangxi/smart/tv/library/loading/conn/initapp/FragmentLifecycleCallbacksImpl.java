package com.tushuangxi.smart.tv.library.loading.conn.initapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.vise.log.ViseLog;

/**
 */
public class FragmentLifecycleCallbacksImpl extends FragmentManager.FragmentLifecycleCallbacks {

    String TAG = "TAG: "+ FragmentLifecycleCallbacksImpl.class.getSimpleName()+"....";
    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentCreated(fm, f, savedInstanceState);
        ViseLog.w("onFragmentCreated: "+f.getClass().getSimpleName());

    }

    @Override
    public void onFragmentDetached(FragmentManager fm, Fragment f) {
        super.onFragmentDetached(fm, f);
        ViseLog.w("onFragmentDetached: "+f.getClass().getSimpleName());
    }
}
