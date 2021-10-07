package com.tushuangxi.smart.tv.net;

import com.xiaomai.environmentswitcher.EnvironmentSwitcher;
import com.xiaomai.environmentswitcher.annotation.Environment;
import com.xiaomai.environmentswitcher.annotation.Module;
import com.xiaomai.environmentswitcher.bean.EnvironmentBean;
import com.xiaomai.environmentswitcher.bean.ModuleBean;

/**
 * 环境配置类</br>
 *
 * ⚠ 建议不要引用该类中的任何子类和成员变量️，一但引用了非正式环境的属性，打包时混淆工具就不会移除该类，导致测试地址泄漏。</br>
 * 而 Environment Switcher 在编译 Release 版本时，会自动隐藏测试环境地址。</br></br>
 *
 * 建议将该类中所有被 {@link Module} 和 {@link Environment} 修饰的类或成员变量用 private 修饰，</br>
 * Environment Switcher 会在编译期间自动生成相应的 Module_XX 和 Environment_XX 静态常量。</br>
 * 例如：通过 EnvironmentSwitcher.MODULE_APP 就可以获取到 App 模块下相应的所有环境</br>
 *
 * 我们在 @Environment 中指定 isRelease = true 的地址，默认选中。
 * 一个 Module 中必须有且只有一个 Environment 的 isRelease 的值为 true，否则编译会失败。
 */
public class EnvironmentConfig {

    static String TAG = "TAG: "+ EnvironmentConfig.class.getSimpleName()+"....";
    /**
     * 整个 App 的环境
     */
    @Module
    private class App {
        @Environment(url = "http://xxp.hangtianyun.net/hengyuaniot-party-building/", isRelease = true, alias = "正式")
        private String online;

        @Environment(url = "https://qa.hangtianyun.net/hengyuaniot-party-building/", alias = "qa")
        private String qa;
    }


    /**
     * 特殊模块 直播 的环境
     */
    @Module(alias = "直播")
    private class Live {
        @Environment(url = "http://live.hengyuanguochuang.com/index3.html/", isRelease = true, alias = "正式")
        private String release;

        @Environment(url = "http://qa.hengyuanguochuang.com/index3.html/", alias = "qa")
        private String qa;

    }

    @Module(alias = "日志")
    private class Log {
        @Environment(url = "false", isRelease = true, alias = "关闭")
        private String close;
        @Environment(url = "true", alias = "开启")
        private String open;
    }


    public static void LogSwitcher(ModuleBean module, EnvironmentBean newEnvironment) {
        if (module.equals(EnvironmentSwitcher.MODULE_LOG)) {
            if (newEnvironment.equals(EnvironmentSwitcher.LOG_OPEN_ENVIRONMENT)) {
                android.util.Log.w(TAG, "openLog");
            } else if (newEnvironment.equals(EnvironmentSwitcher.LOG_CLOSE_ENVIRONMENT)) {

            }
        }
    }

}
