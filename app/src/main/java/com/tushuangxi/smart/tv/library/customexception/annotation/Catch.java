package com.tushuangxi.smart.tv.library.customexception.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Catch {
    /**
     * process函数所在的类
     */
    Class<?> processClass() default NULL.class;

    /**
     * 处理函数的名称
     */
    String process() default "";
}
