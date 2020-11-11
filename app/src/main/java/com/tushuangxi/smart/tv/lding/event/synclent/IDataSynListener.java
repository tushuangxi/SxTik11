package com.tushuangxi.smart.tv.lding.event.synclent;


/**
 * 赞同步接口   声明一个数据同步接口
 */
public interface IDataSynListener {

    /**
     *   多模板截屏
     * @param eventClientMessage
     */
    void onDataSynEvent(DataSynMessage eventClientMessage);

}