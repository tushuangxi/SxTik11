package com.tushuangxi.smart.tv.library.updater;

/**
 * author： liguangwei
 * date： 2019/08/27
 *
 * https://github.com/lgwguang/AppUpdate
 *
 * 该项目是用于更新检测 适配了安卓8.0及9.0
 *
 * /**
 *  * http://59.110.162.30/app_updater_version.json
 *  *
 *  * {
 *  *     "title":"4.5.0更新啦！",
 *  *     "content":"1. 优化了阅读体验；\n2. 上线了 hyman 的课程；\n3. 修复了一些已知问题。",
 *  *     "url":"http://59.110.162.30/v450_imooc_updater.apk",
 *  *     "md5":"14480fc08932105d55b9217c6d2fb90b",
 *  *     "versionCode":"450"
 *  * }
 *  */

public class AppUpdater {

    private static AppUpdater appUpdater = new AppUpdater();

    private INetManager iNetManager = new OkHttpNetManager();

    public void setNetManager(INetManager iNetManager){
        this.iNetManager = iNetManager;
    }

    public INetManager getNetManager(){
        return iNetManager;
    }

    public static AppUpdater getInstance(){
        return appUpdater;
    }



}
