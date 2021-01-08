package com.tushuangxi.smart.tv.library.asyncchain;


import com.tushuangxi.smart.tv.library.asyncchain.core.AsyncChainLink;
import com.tushuangxi.smart.tv.library.asyncchain.core.AsyncChainRunnable;

/**
 * 一部链式调用的入口类
 *
 * https://github.com/Luomingbear/AsyncChain
 * 异步链式库，类似RXJava，可以用于切换主线程，或者执行一串相互依赖的任务
 *
 *      AsyncChain.withWork(new AsyncChainRunnable(){
 *                     @Override
 *                     public void run(AsyncChainTask task){
 *                         //执行一个异步操作
 * //                        doSomething1(new someCallback1(){
 * //                            void callback(newResult){
 * //                                //标识一个行为出错了
 * //                                 task.onError(new AsyncChainError(""))
 * //                                //标识一个异步操作的结束，进行下一步操作
 * //                                task.onNext(newResult);
 * //                            }
 * //                        })
 *                         task.onError(new AsyncChainError("出错了"));
 *                         Logger.w("出错了");
 *                         task.onNext("继续");
 *                         Logger.w(TAG,"继续");
 *                     }
 *                 })
 *                 .withMain(new AsyncChainRunnable(){
 *                     @Override
 *                     public void run(AsyncChainTask task){
 *                         //使用异步操作的结果更新UI
 * //                        updateUI(lastResult);
 *                         //标识整个异步链式结束了，即使后面还有行为没有执行也不会继续下去了
 *                         task.onComplete();
 *                         Logger.w(TAG,"异步链式结束了");
 *                     }
 *                 })
 *                 .errorMain(new AsyncChainErrorCallback(){
 *                     @Override
 *                     public void error(AsyncChainError error) throws Exception {
 *                         //在主线程处理错误
 *                         //一旦error*方法执行，异步链就中断了
 *                         Logger.w(TAG,"异步链就中断了");
 *                     }
 *                 })
 *                 .go(mContext);
 *
 *         //延迟1000毫秒，然后Toast提示
 *         AsyncChain.delay(1000)
 *                 .withMain(new AsyncChainRunnable() {
 *                     @Override
 *                     public void run(AsyncChainTask task) throws Exception {
 *                         Logger.w(TAG,"延迟1000毫秒");
 *                         task.onComplete();
 *                     }
 *                 }).go(this);
 **/
public class AsyncChain {

    /**
     * 开启一个异步链式链，在原始线程执行
     *
     * @param runnable 需要异步执行的操作
     * @return 一个异步链式的链
     */
    public static AsyncChainLink with(AsyncChainRunnable runnable) {
        AsyncChainLink link = new AsyncChainLink();
        return link.with(runnable);
    }

    /**
     * 开启一个异步链式链，在工作线程执行
     *
     * @param runnable 需要异步执行的操作
     * @return 一个异步链式的链
     */
    public static AsyncChainLink withWork(AsyncChainRunnable runnable) {
        AsyncChainLink link = new AsyncChainLink();
        return link.withWork(runnable);
    }

    /**
     * 开启一个异步链式链，在主线程执行，用于更新UI
     *
     * @param runnable 需要异步执行的操作
     * @return 一个异步链式的链
     */
    public static AsyncChainLink withMain(AsyncChainRunnable runnable) {
        AsyncChainLink link = new AsyncChainLink();
        return link.withMain(runnable);
    }

    /**
     * 延迟一定时长执行操作，仅支持在工作线程做延时操作
     *
     * @param ms 延迟的时间单位毫秒
     * @return 一个异步链式的链
     */
    public static AsyncChainLink delay(long ms) {
        AsyncChainLink link = new AsyncChainLink();
        return link.delay(ms);
    }
}
