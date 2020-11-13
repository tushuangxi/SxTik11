package com.tushuangxi.smart.tv.library.imageloaderfactory.picasso;

import android.widget.ImageView;

import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageConfigProduct;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageLoaderProduct;

/**
 * Created by Administrator on 2016/3/2.
 */
public class PicassoLoaderProduct implements ImageLoaderProduct {
    @Override
    public void display(String imageUri, ImageView imageView) {
        PicassoWrapper.getDefalt().getPicasso()
                .load(imageUri).into(imageView);
    }

    @Override
    public void display(String imageUrl, ImageView imageView, ImageConfigProduct config) {
        PicassoConfigProduct mConfig = (PicassoConfigProduct) config.get();
        PicassoWrapper.getDefalt()
                .getPicasso()
                .load(imageUrl)
//                .placeholder(mConfig.loadingRes) // can also be a drawable
//                .error(mConfig.failRes) // will be displayed if the image cannot be loaded
                .into(imageView);
    }

    @Override
    public void cleanImageCache(String url) {
        PicassoWrapper.getDefalt()
                .getPicasso()
                .invalidate(url);
    }
}
