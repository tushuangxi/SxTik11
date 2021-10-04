package com.tushuangxi.smart.tv.net;


import com.tushuangxi.smart.tv.lding.http.ApiConstants;
import com.xiaomai.environmentswitcher.annotation.Environment;
import com.xiaomai.environmentswitcher.annotation.Module;

/**
 * 环境配置类</br>
 *
 * ⚠ 建议不要引用该类中的任何子类和成员变量️，一但引用了非正式环境的属性，打包时混淆工具就不会移除该类，导致测试地址泄漏。</br>
 * 而 Environment Switcher 在编译 Release 版本时，会自动隐藏测试环境地址。</br></br>
 *
 * 建议将该类中所有被 {@link Module} 和 {@link Environment} 修饰的类或成员变量用 private 修饰，</br>
 * Environment Switcher 会在编译期间自动生成相应的 Module_XX 和 Environment_XX 静态常量。</br>
 * 例如：通过 EnvironmentSwitcher.MODULE_APP 就可以获取到 App 模块下相应的所有环境</br>
 */
public class EnvironmentConfig {

    /**
     * 整个 App 的环境
     */
    @Module
    private class App {
        @Environment(url = "http://xxp.hangtianyun.net/hengyuaniot-party-building/", isRelease = true, alias = "正式")
        private String online;
    }

    /**
     * 特殊模块 Management 的环境
     */
    @Module(alias = "Management")
    private class Management {
        @Environment(url = "http://management.hengyuanguochuang.com/", isRelease = true, alias = "正式")
        private String online;

        @Environment(url = "http://test.hengyuanguochuang.com/", alias = "测试")
        private String test;
    }

    /**
     * 特殊模块 Panel 的环境
     */
    @Module(alias = "Panel")
    private class Panel {
        @Environment(url = "http://panel.hengyuanguochuang.com/index3.html/", isRelease = true, alias = "正式")
        private String release;

        @Environment(url = "http://panel.hengyuanguochuang.com/index3.html/", alias = "测试")
        private String test;

        @Environment(url = "http://panel.hengyuanguochuang.com/index3.html/")
        private String test1;

        @Environment(url = "http://panel.hengyuanguochuang.com/index3.html/", alias = "沙箱")
        private String sandbox;
    }

    /**
     * 特殊模块 活动 的环境
     */
    @Module(alias = "Shaky")
    private class Shaky {
        @Environment(url = "http://shaky.hengyuanguochuang.com/index3.html/", isRelease = true, alias = "正式")
        private String online;

        @Environment(url = "http://shaky.hengyuanguochuang.com/index3.html/", alias = "测试")
        private String test;
    }
}
