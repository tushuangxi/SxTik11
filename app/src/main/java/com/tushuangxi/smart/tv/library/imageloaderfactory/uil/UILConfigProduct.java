package com.tushuangxi.smart.tv.library.imageloaderfactory.uil;

import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageConfigProduct;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageLoaderWrapper;

/**
 * Created by Administrator on 2016/2/29.
 */
public class UILConfigProduct implements ImageConfigProduct {
    //default true
    public boolean isSupportMemoryCache;
    public boolean isSupportDiskCache;
    public int defaulRes;
    public int loadingRes;
    public int failRes;
    public int duration;

    public UILConfigProduct() {
        this.isSupportMemoryCache = true;
        this.isSupportDiskCache = true;
        this.loadingRes = 0;
        this.failRes = 0;
        this.duration = 0;
    }

    @Override
    public void setDefaulRes(int defaulRes) {
        this.defaulRes = defaulRes;
    }

    @Override
    public void setLoadingRes(int loadingRes) {
        this.loadingRes = loadingRes;
    }

    @Override
    public void setFailRes(int failRes) {
        this.failRes = failRes;
    }

    @Override
    public void setsupportMemoryCache(boolean flag) {
        this.isSupportMemoryCache = flag;
    }

    @Override
    public void setsupportDiskCache(boolean flag) {
        this.isSupportDiskCache = flag;
    }

    @Override
    public void setFadeIn(int duration) {
        this.duration = duration;
    }

    @Override
    public Object get() {
        ImageLoaderWrapper.DisplayConfig config = new ImageLoaderWrapper.DisplayConfig.Builder().build();
        config.loadFailImageRes = this.loadingRes;
        config.loadFailImageRes = this.failRes;
        config.stubImageRes = this.defaulRes;
        config.supportMemoryCache = this.isSupportMemoryCache;
        config.supportDiskCache = this.isSupportDiskCache;
        return config;
    }
}
