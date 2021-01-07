package com.tushuangxi.smart.tv.lding.entity.livedata;

import androidx.lifecycle.LiveData;

import com.tushuangxi.smart.tv.lding.ui.InitActivity;
import com.tushuangxi.smart.tv.library.mmkv.KVUtils;


/**
 * 封装student成一个livedata的单例，只要student变化了 就能通知接收者
 *
 * @author Beanu
 */
public class DataLiveData extends LiveData<Data> {

    private static DataLiveData sIns;

    private DataLiveData() {
    }

    public static DataLiveData getInstance() {
        if (sIns == null) {
            sIns = new DataLiveData();
        }
        return sIns;
    }

    @Override
    protected void onActive() {
        if (getValue() == null) {
//            Data data = new Data();
            if (InitActivity.data != null) {
                setValue(InitActivity.data);
            }
        }

    }

    @Override
    protected void onInactive() {

    }

    @Override
    public void setValue(Data value) {
        super.setValue(value);
        //实例化到本地
        KVUtils.getInstance().putString("student", value.getName());
    }

    @Override
    public void postValue(Data value) {
        super.postValue(value);
        //最终调用setValue
    }

}
