package com.tushuangxi.smart.tv.lding.event.eventclent;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;


/**
 * @Created by changliang on 2017/6/19.
 *
 *
 *   this.getNettyClientHelper().notifyEvent(new LdingNotifierEvent(Event.NETTY_SESSION_AUTHENTICATED));
 */
public class EventClientHelper {

    private static EventClientHelper instance = null;

    // 使用回调的方式替换broadcast通知,以便开发者更容易定制自己的实现例如免打扰功能
    private ArrayList<EventClientListener> eventListeners;


    private EventClientHelper() {
        // 初始化事件监听器
        eventListeners = new ArrayList<EventClientListener>();
    }

    /**
     * get global instance
     *
     * @return
     */
    public static EventClientHelper getInstance() {
        if (instance == null) {
            synchronized (EventClientHelper.class) {
                if (instance == null) {// 多线程安全单例模式(使用双重同步锁)
                    instance = new EventClientHelper();
                }
            }
        }
        return instance;
    }


    /**
     * register event listener(注册事件监听器)
     *
     * @param ldingEventListener
     */
    public void registerEventListener(EventClientListener ldingEventListener) {
        synchronized (eventListeners) {
            if (eventListeners.contains(ldingEventListener)) {
                return;
            }
            eventListeners.add(ldingEventListener);
        }
    }

    /**
     * un register event listener
     *
     * @param ldingEventListener
     */
    public void unregisterEventListener(EventClientListener ldingEventListener) {
        synchronized (eventListeners) {
            int position = 0;
            while (position < eventListeners.size()) {
                EventClientListener eventListener = eventListeners.get(position);
                if (ldingEventListener.equals(eventListener)) {
                    eventListeners.remove(eventListener);
                } else {
                    position++;
                }
            }
        }
    }

    public void clearEventListeners() {
        eventListeners.clear();
    }

    /**
     * notify all registered listener
     *
     * @param notifierEvent
     */
    public void notifyEvent(EventClientMessage notifierEvent) {
        //响应事件,调用回调函数
        if (eventListeners != null && eventListeners.size() > 0) {
            for (int i = eventListeners.size() - 1; i > -1; i--) {
                EventClientListener eventListener = eventListeners.get(i);
                boolean isDealWith = eventListener.onEvent(notifierEvent);
                if (isDealWith) {
                    break;
                }
            }
        }
    }

    public void release() {
        //清空监听器
        clearEventListeners();
        //清空单例
        instance = null;
    }

    class TaskHandler extends Handler {

        public TaskHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
