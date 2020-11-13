package com.tushuangxi.smart.tv.library.imageloaderfactory.image;

/**
 * Created by Administrator on 2016/2/29.
 */
public interface ImageConfigProduct {
    void setDefaulRes(int defaulRes);
    void setLoadingRes(int loadingRes);
    void setFailRes(int failRes);
    void setsupportMemoryCache(boolean flag);
    void setsupportDiskCache(boolean flag);
    void setFadeIn(int duration);
    Object get();
}
