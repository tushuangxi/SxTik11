package com.tushuangxi.smart.tv.library.loading.conn;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.alpha.AlphaManager;
import com.alibaba.android.alpha.ITaskCreator;
import com.alibaba.android.alpha.OnProjectExecuteListener;
import com.alibaba.android.alpha.Project;
import com.alibaba.android.alpha.Task;
import com.billy.android.swipe.SmartSwipeBack;
import com.tao.admin.loglib.Logger;
import com.tushuangxi.smart.tv.lding.utils.LaunchTimer;
import com.tushuangxi.smart.tv.library.alphatask.TaskA;
import com.tushuangxi.smart.tv.library.alphatask.TaskB;
import com.tushuangxi.smart.tv.library.alphatask.TaskC;
import com.tushuangxi.smart.tv.library.alphatask.TaskD;
import com.tushuangxi.smart.tv.library.alphatask.TaskE;
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


        ////Alpha启动框架 方式1
//        Task a = new TaskA();
//        Task b = new TaskB();
//        Task c = new TaskC();
//        Task d = new TaskD();
//        Task e = new TaskE();
//        Project.Builder builder = new Project.Builder();
//        builder.add(a);
//        builder.add(b).after(a);
//        builder.add(c).after(a);
//        builder.add(d).after(b, c);
//        builder.add(e).after(a);
//        Project project = builder.create();
//        AlphaManager.getInstance(mContext).addProject(project);
//        AlphaManager.getInstance(mContext).start();

        //Alpha启动框架 方式2
//        Project.Builder builder =  new Project.Builder().withTaskCreator(new MyTaskCreator());
//        builder.add(TASK_A);
//        builder.add(TASK_B).after(TASK_A);
//        builder.add(TASK_C).after(TASK_A);
//        builder.add(TASK_D).after(TASK_B, TASK_C);
//        builder.add(TASK_E).after(TASK_C, TASK_D);
//        builder.setProjectName("innerGroup");
//        Project project = builder.create();
//        project.addOnTaskFinishListener(new Task.OnTaskFinishListener() {
//            @Override
//            public void onTaskFinish(String taskName) {
//                Logger.w(TAG, "project....onTaskFinish");
//            }
//        });
//        AlphaManager.getInstance(mContext).addProject(project);
//        AlphaManager.getInstance(mContext).start();

        //Alpha启动框架 方式3  可查询执行结果  执行生命周期的回调，可以监听到开始执行与结束执行
        Project.Builder builder =  new Project.Builder().withTaskCreator(new MyTaskCreator());
        builder.add(TASK_A);
        builder.add(TASK_B).after(TASK_A);
        builder.add(TASK_C).after(TASK_A);
        builder.add(TASK_D).after(TASK_B, TASK_C);
        builder.add(TASK_E).after(TASK_C, TASK_D);
        builder.setProjectName("innerGroup");
        builder.setOnProjectExecuteListener(new OnProjectExecuteListener() {
                    @Override
                    public void onProjectStart() {
                        Logger.w(TAG, "onProjectStart");
                    }

                    @Override
                    public void onTaskFinish(String taskName) {
                        Logger.w(TAG, "onTaskFinish: " + taskName);
                    }

                    @Override
                    public void onProjectFinish() {
                        Logger.w(TAG, "onProjectFinish");
                    }
                });
        Project project = builder.create();
        AlphaManager.getInstance(mContext).addProject(project);
        AlphaManager.getInstance(mContext).start();

        LaunchTimer.endTime();
    }

    private  final String TASK_A = "TaskA";
    private  final String TASK_B = "TaskB";
    private  final String TASK_C = "TaskC";
    private  final String TASK_D = "TaskD";
    private  final String TASK_E = "TaskE";
    public  class MyTaskCreator implements ITaskCreator {
        @Override
        public Task createTask(String taskName) {
            Logger.w(TAG, "==ALPHA==" + taskName);
            switch (taskName) {
                case TASK_A:
                    return new TaskA();
                case TASK_B:
                    return new TaskB();
                case TASK_C:
                    return new TaskC();
                case TASK_D:
                    return new TaskD();
                case TASK_E:
                    return new TaskE();
                    default:
            }
            return null;
        }
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
