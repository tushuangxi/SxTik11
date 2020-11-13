package com.tushuangxi.smart.tv.library.imageloaderfactory.picasso;

import android.content.Context;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/3/1.
 */
public class PicassoWrapper {
    private static PicassoWrapper sDefaultInstance;
    private Context mContext;

    public PicassoWrapper(Context context) {
        mContext = context;
    }

    public static PicassoWrapper init(Context context) {
        if (sDefaultInstance == null) {
            sDefaultInstance = new PicassoWrapper(context);
        }
        return sDefaultInstance;
    }


    public static PicassoWrapper getDefalt() {
        if (sDefaultInstance == null) {
            throw new RuntimeException(
                    "Must be call init(Context) befor!");
        }
        return sDefaultInstance;
    }


    public Picasso getPicasso(){
        /**
         * setIndicatorsEnabled(true);  查看图片都来源于何处,缓存指示器
         * 蓝色 - 从内存中获取,是最佳性能展示
         * 绿色 - 从本地获取,性能一般
         * 红色 - 从网络加载,性能最差
         */
        Picasso.with(mContext).setIndicatorsEnabled(false);
        return Picasso.with(mContext);
    }

}
