package com.tushuangxi.smart.tv.library.imageloaderfactory.image;

import android.widget.ImageView;

/**
 * Created by Administrator on 2016/2/29.
 */
public interface ImageLoaderProduct {
    void display(String imageUri, ImageView imageView);
    void display(String imageUrl, ImageView imageView, ImageConfigProduct config);
    void cleanImageCache(String imageUrl);
}
