package com.tushuangxi.smart.tv.library.imageloaderfactory.picasso;

import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageConfigProduct;

/**
 * Created by Administrator on 2016/3/2.
 */
public class PicassoConfigProduct implements ImageConfigProduct {
    //default true
    public boolean isSupportMemoryCache;
    public boolean isSupportDiskCache;
    public int defaulRes;
    public int loadingRes;
    public int failRes;
    public int duration;


    public PicassoConfigProduct() {
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
        return this;
    }
}
