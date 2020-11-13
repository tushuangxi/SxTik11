package com.tushuangxi.smart.tv.library.imageloaderfactory.glide;

import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageConfigProduct;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageFrameworkFactory;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageLoaderProduct;

/**
 * Created by Administrator on 2016/2/29.
 */
public class GlideFrameworkFactory implements ImageFrameworkFactory {

    @Override
    public ImageLoaderProduct createImageLoader() {
        return new GlideLoaderProduct();
    }

    @Override
    public ImageConfigProduct createImageConfig() {
        return new GlideConfigProduct();
    }
}
