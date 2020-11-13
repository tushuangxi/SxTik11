package com.tushuangxi.smart.tv.library.imageloaderfactory.picasso;

import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageConfigProduct;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageFrameworkFactory;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageLoaderProduct;

/**
 * Created by Administrator on 2016/3/2.
 */
public class PicassoFramworkFactory implements ImageFrameworkFactory {
    @Override
    public ImageLoaderProduct createImageLoader() {
        return new PicassoLoaderProduct();
    }

    @Override
    public ImageConfigProduct createImageConfig() {
        return new PicassoConfigProduct();
    }
}
