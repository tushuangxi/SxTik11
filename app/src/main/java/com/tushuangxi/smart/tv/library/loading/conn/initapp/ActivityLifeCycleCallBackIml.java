package com.tushuangxi.smart.tv.library.loading.conn.initapp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import com.tao.admin.loglib.Logger;
import com.tushuangxi.smart.tv.lding.utils.ActivityUtils;
import java.util.HashMap;


/**
 * activity生命周期管理类
 * 你想象力有多丰富,这里就有多强大,
 * 以前放到BaseActivity的操作都可以放到这里
 * 使用:registerActivityLifecycleCallbacks(new ActivityLifeCycleCallBackIml());
 */
public class ActivityLifeCycleCallBackIml implements Application.ActivityLifecycleCallbacks {

    String TAG = "TAG: "+ ActivityLifeCycleCallBackIml.class.getSimpleName()+"....";
    private HashMap<String, FragmentLifecycleCallbacksImpl> lifecycleCallbacksHashMap=new HashMap<>();
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ActivityUtils.addActivity(activity);
        if (activity instanceof FragmentActivity){
            FragmentLifecycleCallbacksImpl lifecycleCallbacks = new FragmentLifecycleCallbacksImpl();
            lifecycleCallbacksHashMap.put(activity.getClass().getSimpleName(),lifecycleCallbacks);
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(lifecycleCallbacks,true);
        }

        Logger.w(TAG,"activity生命周期管理类:  onActivityCreated: "+activity.getLocalClassName());

    }

    @Override
    public void onActivityStarted(final Activity activity) {
        //这里根据不同的activity显示不同的topBar
        appCount++;
        if (isRunInBackground) {
            //应用从后台回到前台 需要做的操作
            Logger.w(TAG,"切换到前台......");
        }

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        appCount--;
        if (appCount == 0) {
            Logger.w(TAG,"切换到后台......");

        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Logger.w(TAG,"activity生命周期管理类:  "+"onActivityDestroyed: "+activity.getLocalClassName());
        ActivityUtils.removeActivity(activity);

    }

    int appCount = 0;
    boolean isRunInBackground = false;
    /**
     * 从后台回到前台需要执行的逻辑
     * @param activity
     */
    private void back2App(Activity activity) {
        isRunInBackground = false;
    }

    /**
     * 离开应用 压入后台或者退出应用
     * @param activity
     */
    private void leaveApp(Activity activity) {
        isRunInBackground = true;
    }

}
