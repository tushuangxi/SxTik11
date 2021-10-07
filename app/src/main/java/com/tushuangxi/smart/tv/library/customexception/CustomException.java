package com.tushuangxi.smart.tv.library.customexception;

import com.tushuangxi.smart.tv.library.customexception.annotation.Catch;

/**
 * 自定义的异常,当异常被抛出后,会自动回调onCatchThrowable函数.
 *
 *    //点击会立刻退出
 *    throw new RuntimeException("我是一个RuntimeException.");
 *
 *      throw new Error("我是一个Error :)");
 *
 *        执行 throwCustomException();
 *
 *      抛出一个自定义异常
 *     private void throwCustomException(){
 *           throw new CustomException();
 *       }
 */
@Catch(process = "onCatchThrowable")
public class CustomException extends IllegalAccessError {

   static String TAG = "TAG: "+ CustomException.class.getSimpleName()+"....";
	private static final long serialVersionUID = 4066053857828321133L;

	public static void onCatchThrowable(Thread t){
//        ViseLog.w(TAG,"我被抛出了...");
    }
}
