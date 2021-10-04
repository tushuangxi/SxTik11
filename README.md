//https://github.com/CodeXiaoMai/EnvironmentSwitcher 一键切换正式/测试环境
1 配置项目的 build.gradle
  java 版
  dependencies {
      ...
      implementation "com.xiaomai.environmentswitcher:environmentswitcher:$version"
      debugAnnotationProcessor "com.xiaomai.environmentswitcher:environmentswitcher-compiler:$version"
      releaseAnnotationProcessor "com.xiaomai.environmentswitcher:environmentswitcher-compiler-release:$version"
  // 如果你的项目中还包含自定义的 flavor，还需要针对每个 flavor 配置
  }


  kotlin 版
  apply plugin: 'kotlin-kapt'
  dependencies {
      ...
      implementation "com.xiaomai.environmentswitcher:environmentswitcher:$version"
      kaptDebug "com.xiaomai.environmentswitcher:environmentswitcher-compiler:$version"
      kaptRelease "com.xiaomai.environmentswitcher:environmentswitcher-compiler-release:$version"
  // 如果你的项目中还包含自定义的 flavor，还需要针对每个 flavor 配置
  }



2 编写 EnvironmentConfig 文件
    这个类是 Environment Switcher 依赖的核心代码，所有获取、修改环境的逻辑代码都会依赖这个类中被 @Module 和 @Environment 两个注解标记的类和属性自动生成。
    注意：如果你的项目中使用了 Kotlin，请使用 Java 语言编写 EnvironmentConfig，就像在 GreenDao 中必须使用 Java 语言编写 Entity 类一样。


3 添加入口
  手动切换环境当然要有一个页面，这个页面 Environment Switcher 已经自动集成了，只需要添加一个入口跳转即可（这个入口只在 Debug 测试等内部版显示）。
  例如：在“我的”页面中。