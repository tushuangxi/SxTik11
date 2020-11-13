package com.tushuangxi.smart.tv.library.imageloaderfactory.uil;

import android.widget.ImageView;

import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageConfigProduct;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageLoaderProduct;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageLoaderWrapper;

/**
 * Created by Administrator on 2016/2/29.
 */
public class UILLoaderProduct implements ImageLoaderProduct {

    @Override
    public void display(String imageUri, ImageView imageView) {
        ImageLoaderWrapper.getDefault().displayImage(imageUri, imageView);
    }

    @Override
    public void display(String imageUrl, ImageView imageView, ImageConfigProduct config) {
        ImageLoaderWrapper.DisplayConfig mConfig = (ImageLoaderWrapper.DisplayConfig) config.get();
        ImageLoaderWrapper.getDefault().displayImage(imageUrl, imageView, mConfig);
    }

    @Override
    public void cleanImageCache(String url) {
        ImageLoaderWrapper.getDefault().clearDefaultLoaderCache(url);

    }
}
