package com.tushuangxi.smart.tv.library.loading.conn;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.billy.android.swipe.SmartSwipeBack;
import com.tao.admin.loglib.Logger;
import com.tushuangxi.smart.tv.lding.utils.LaunchTimer;
import com.tushuangxi.smart.tv.library.taskly.TaskDispatcher;
import com.tushuangxi.smart.tv.library.taskly.tasks.GetDeviceIdTask;
import com.tushuangxi.smart.tv.library.taskly.tasks.InitMainThreadWatchDogTask;
import com.tushuangxi.smart.tv.library.taskly.tasks.InitUiStatusTask;
import com.tushuangxi.smart.tv.library.taskly.tasks.InitImageLoadTask;
import com.tushuangxi.smart.tv.library.taskly.tasks.InitJPushTask;
import com.tushuangxi.smart.tv.library.taskly.tasks.InitSplashKitTask;
import com.tushuangxi.smart.tv.library.taskly.tasks.InitUiWatchTask;
import com.tushuangxi.smart.tv.library.taskly.tasks.InitUmengTask;
import com.tushuangxi.smart.tv.library.taskly.tasks.InitWelikeCatchTask;

//枚举  任务   不需要static  也能调用
public enum PreTaskManager {



    /**
     * $ 枚举常量  可以任意定义  .使用类名直接调用
     */
    $;
    String TAG = "TAG: "+ PreTaskManager.class.getSimpleName()+"....";
    private Application app;
    private Context mContext;

    public  void init(Application app, Context context) {
        this.app = app;
        this.mContext = context;

        swipeBack(app);
        executePre(app,context);
    }

    private  void executePre(Application app, Context context) {

        //多任务 推荐方式一      task  封装处理
        TaskDispatcher.init(mContext);
        TaskDispatcher dispatcher = TaskDispatcher.createInstance();
        dispatcher.addTask(new InitUiWatchTask())
                .addTask(new InitMainThreadWatchDogTask())
                .addTask(new InitSplashKitTask(app))
                .addTask(new InitWelikeCatchTask(mContext))
                .addTask(new InitUiStatusTask())
                .addTask(new InitImageLoadTask(mContext))
                .addTask(new InitJPushTask())
                .addTask(new InitUmengTask())
                .addTask(new GetDeviceIdTask())
               .start();
        //启动器中配置需要等待的函数没有完成是都会等待
        dispatcher.await();

        LaunchTimer.endTime();
    }

    private void swipeBack(Application app) {

        // Activity 侧滑返回
//        SmartSwipeBack.activitySlidingBack(app, activity -> {
//            if (activity instanceof SwipeAction) {
//                return ((SwipeAction) activity).isSwipeEnable();
//            }
//            return true;
//        });

        SmartSwipeBack.activitySlidingBack(app, new SmartSwipeBack.ActivitySwipeBackFilter() {
            @Override
            public boolean onFilter(Activity activity) {
                if (activity instanceof SwipeAction) {
                    return ((SwipeAction) activity).isSwipeEnable();
                }
                return true;
            }
        });
    }

    public interface SwipeAction {

        /**
         * 是否使用侧滑
         */
        default boolean isSwipeEnable() {
            // 默认开启  true
            return false;
        }
    }
}
