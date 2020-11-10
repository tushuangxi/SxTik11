package com.tushuangxi.smart.tv.library.customexception.exception;


import com.tushuangxi.smart.tv.library.customexception.annotation.UnCatch;

/**
 * 继承本类的Throwable将不会被异常隔离机制拦截,直接抛出
 */
@UnCatch
public class UncaughtThrowable extends NullPointerException {

	private static final long serialVersionUID = 234876922020326875L;

}
