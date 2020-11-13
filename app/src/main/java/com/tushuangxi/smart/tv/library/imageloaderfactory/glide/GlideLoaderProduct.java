package com.tushuangxi.smart.tv.library.imageloaderfactory.glide;

import android.widget.ImageView;

import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageConfigProduct;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageLoaderProduct;

/**
 * Created by Administrator on 2016/3/1.
 */
public class GlideLoaderProduct implements ImageLoaderProduct {
    @Override
    public void display(String imageUri, ImageView imageView) {
        GlideWrapper.getDefalt()
                .getGlide()
                .load(imageUri)
//                .centerCrop()
                .into(imageView);
    }

    @Override
    public void display(String imageUrl, ImageView imageView, ImageConfigProduct config) {
        GlideConfigProduct mConfig = (GlideConfigProduct) config.get();
        GlideWrapper.getDefalt()
                .getGlide()
                .load(imageUrl)
//                .centerCrop()
//                .placeholder(mConfig.loadingRes) // can also be a drawable
//                .error(mConfig.failRes) // will be displayed if the image cannot be loaded
//                .crossFade(mConfig.duration)
                .into(imageView);
    }

    @Override
    public void cleanImageCache(String url) {
        GlideWrapper.getDefalt().cleanCache();
    }
}
