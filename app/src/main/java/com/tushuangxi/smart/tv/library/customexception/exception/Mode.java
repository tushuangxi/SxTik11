package com.tushuangxi.smart.tv.library.customexception.exception;

/**
 * <b>异常安全隔离机制</b>的模式枚举类.

 */
public enum Mode {
    /**
     * 除非异常上包含{@link com.tushuangxi.smart.tv.library.customexception.annotation.UnCatch}
     * 注解,否则一律捕获.
     */
    THROW_IF_UN_CATCH,
    /**
     * 不捕获任何异常,遇到异常直接杀掉自己(只用于调试)
     */
    DONT_CATCH,
    /**
     * 无论是否包含注解,一律捕获异常.
     */
    CATCH_ALL
}
