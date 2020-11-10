package com.tushuangxi.smart.tv.lding.other;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

/**
 * Created by
 * common的常量类
 */

public class CommonLibConstant {
    private static final CommonLibConstant libConstant = new CommonLibConstant();

    public static CommonLibConstant init() {
        return libConstant;
    }

    public static Context applicationContext;
    public static String noNetWorkRemind = "网络连接已断开,请检查网络";
    //是否是调试模式，默认是调试模式
    public static boolean IS_DEBUG = true;
    private String LOCAL_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/";

    /**
     * 设置应用的Context，必须先设置，必须设置
     *
     * @param appContext
     * @return
     */
    public CommonLibConstant setAppContext(Context appContext) {
        applicationContext = appContext;
        return this;
    }

    /**
     * 设置是不是Debug模式
     *
     * @param isDebug
     * @return
     */
    public CommonLibConstant setIsDebug(boolean isDebug) {
        IS_DEBUG = isDebug;
        return this;
    }

    /**
     * 设置无网络提示语
     *
     * @param noNetremind
     * @return
     */
    public CommonLibConstant setNoNetWorkRemind(String noNetremind) {
        noNetWorkRemind = noNetremind;
        return this;
    }

    /**
     * 设置SharedPreferences的存储名以及初始化
     *
     * @param dbName
     * @return
     */
    public CommonLibConstant setSharedPreferencesName(String dbName) {
        if (TextUtils.isEmpty(dbName)) {
            dbName = "sp_app";
        }
//        AppSharedPreHelper.init().initDB(applicationContext, dbName);
        return this;
    }

}
