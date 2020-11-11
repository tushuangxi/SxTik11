package com.tushuangxi.smart.tv.lding.event.synclent;

import android.os.Handler;

import com.tushuangxi.smart.tv.lding.event.eventclent.EventClientMessage;

import java.util.LinkedList;

/**  观察者管理类实现
 * 定义一个单例管理监听总线
 *
 *  优点：相对广播传输安全一点，对于总线数量过大的时候效率可能会比较低。
 *  缺点：不能设置优先级，不能终止传递，不能修改消息。
 *
 *  2.在Activity对应的生命周期添加监听/移除监听 onCreate/onStart/onResume 添加监听 onDestroy/onStop/onPause 移除监听
 * 添加监听
 *   DataSynManager.getInstance().registerDataSynListener(dataSynListener);
 *
 * 移除监听
 * DataSynManager.getInstance().unRegisterDataSynListener(dataSynListener);
 *
 * 声明一个监听
 *    DataSynManager.IDataSynListener dataSynListener=new DataSynManager.IDataSynListener() {
 *         @Override
 *         public void onDataSyn(int count) {
 *             //接下来执行同步操作
 *         }
 *     };
 *
 * 3.在触发数据同步的地方发送消息
 *   DataSynManager.getInstance().doDataSyn(5);
 *
 *  EventBus出现之前所使用的实现方式。
 */
public class DataSynManager {

    private LinkedList<IDataSynListener> autoListeners = new LinkedList();//监听集合
    private static DataSynManager mInstance;//单例引用


    /**
     * 获取单例引用
     *
     * @return
     */
    public static DataSynManager getInstance() {
        if (mInstance == null) {
            synchronized (DataSynManager.class) {
                if (mInstance == null) {
                    mInstance = new DataSynManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 添加同步数据监听
     */
    public void registerDataSynListener(IDataSynListener autoDataListener) {
        if (autoListeners == null) {
            autoListeners = new LinkedList<IDataSynListener>();
        }
        if (!autoListeners.contains(autoDataListener)) {
            autoListeners.add(autoDataListener);
        }
    }

    /**
     * 移除同步数据监听
     */
    public void unRegisterDataSynListener(IDataSynListener autoDataListener) {
        if (autoListeners == null) {
            return;
        }
        if (autoListeners.contains(autoDataListener)) {
            autoListeners.remove(autoDataListener);
        }
    }

    /**
     * 清除所有监听者
     */
    public void release() {
        if (autoListeners != null) {
            autoListeners.clear();
            autoListeners = null;
        }
    }

    /**
     * 执行数据同步  多模板截屏
     * @param dataSynMessage
     */
    public void onDataSynEventMessage(DataSynMessage dataSynMessage) {
        if (autoListeners == null) {
            autoListeners = new LinkedList();
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                for (IDataSynListener dataSynListener : autoListeners) {
                    dataSynListener.onDataSynEvent(dataSynMessage);
                }
            }
        });
    }

}