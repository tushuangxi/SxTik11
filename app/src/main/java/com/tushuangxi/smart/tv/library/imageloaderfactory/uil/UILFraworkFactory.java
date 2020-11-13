package com.tushuangxi.smart.tv.library.imageloaderfactory.uil;

import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageConfigProduct;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageFrameworkFactory;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageLoaderProduct;

/**
 * Created by Administrator on 2016/2/29.
 */
public class UILFraworkFactory implements ImageFrameworkFactory {
    @Override
    public ImageLoaderProduct createImageLoader() {
        return new UILLoaderProduct();
    }

    @Override
    public ImageConfigProduct createImageConfig(){
        return new UILConfigProduct();
    }
}
