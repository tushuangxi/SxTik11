package com.tushuangxi.smart.tv.library.taskly.tasks;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.tushuangxi.smart.tv.lding.other.AppGlobalConsts;
import com.tushuangxi.smart.tv.lding.utils.DateUtil;
import com.tushuangxi.smart.tv.lding.utils.LogcatUtils;
import com.tushuangxi.smart.tv.lding.utils.SpfsUtils;
import com.tushuangxi.smart.tv.library.taskly.task.MainTask;
import com.king.thread.nevercrash.NeverCrash;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 主线程执行的task(需要在onCreate()方法中执行完成)
 *
 * 异常捕获
 */
public class InitWelikeCatchTask extends MainTask {

    String TAG = "TAG: "+ InitWelikeCatchTask.class.getSimpleName()+"....";
    private Context mContext;
    public InitWelikeCatchTask(Context mContext) {
        super();
        this.mContext = mContext;
    }

    /**
     * 异步线程执行的Task是否需要在被调用await的时候等待，默认不需要
     * @return
     */
    @Override
    public boolean needWait() {

        return true;
    }

    @Override
    public void run() {

        //方式一  开启异常安全隔离机制
//        WelikeGuard.enableGuard();
//        //此时只要抛出的不是RuntimeException,UI线程就会继续跑下去
//        WelikeGuard.registerUnCaughtHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread thread, Throwable ex) {
//                ex.printStackTrace();
//                ALogger.e(TAG,"出现异常了: " + ex.getMessage() + " (" + ex.getClass().getSimpleName() + ")" );
                 //上传异常信息到服务器
//                 uploadExceptionToServer(thread ,ex);
//            }
//        });

        //方式二推荐  报错不会崩溃   全局捕获Crash。信NeverCrash，永不Crash。
       /* NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
//                ALogger.e(TAG, Log.getStackTraceString(ex));
                ex.printStackTrace();

                //上传异常信息到服务器
                uploadExceptionToServer(thread ,ex);
                //保存本地日志
                LogcatUtils.exportLogcat(ex.getStackTrace().toString());
//                showToast(e.getMessage());
            }
        });*/


       
    }

    /**
     * 上传到服务器
     * @param t
     * @param e
     */
    public void uploadExceptionToServer(Thread t, Throwable e) {
        final StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            printWriter.write("APPVersionCode=" + pi.versionCode + ", APPVersionName=" + pi.versionName + "\r\n");
        } catch (PackageManager.NameNotFoundException nnfe) {
        }
        e.printStackTrace(printWriter);

        StringBuffer  crashInfo = new StringBuffer();
        //时间
        crashInfo.append(DateUtil.myNowSystemtime()).append("\n");
        //手机信息
        crashInfo.append(getPhoneInfo()).append("\n");
        //奔溃信息
        crashInfo.append(throwable2String(e));
        //上传错误信息 stringWriter.toString()  或者 crashInfo.toString()
        String macAddr = SpfsUtils.readString(mContext, AppGlobalConsts.MacAddr);
        uploadCrashLogInfo(macAddr,crashInfo.toString(),1);
//        ALogger.e(TAG,"上传崩溃日志: " + crashInfo.toString() );
    }

    private String throwable2String(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        ex.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

    /**
     * 获取手机信息和应用版本信息
     */
    private String getPhoneInfo() {
        //手机信息
        StringBuffer phoneInfo = new StringBuffer();
        //应用的版本名称和版本号
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //App版本
        phoneInfo.append("App Version: ").append(pi.versionName).append("_").append(pi.versionCode).append("\n");
        //android版本号
        phoneInfo.append("OS Version: ").append(Build.VERSION.RELEASE).append("_").append(Build.VERSION.SDK_INT).append("\n");
        //手机制造商
        phoneInfo.append("Vendor: ").append(Build.MANUFACTURER).append("\n");
        //手机型号
        phoneInfo.append("Model: ").append(Build.MODEL).append("\n");
        //cpu架构
        phoneInfo.append("CPU ABI: ").append(Build.CPU_ABI).append("\n");
        return phoneInfo.toString();
    }

    private void showToast(final String text){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext,text,Toast.LENGTH_SHORT).show();
            }
        });
    }

    //上传崩溃日志
    private void uploadCrashLogInfo(String deviceIdentity,String contentString,int type) {

    }

}
