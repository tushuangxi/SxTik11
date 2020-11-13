package com.tushuangxi.smart.tv.library.imageloaderfactory;

import android.content.Context;
import android.widget.ImageView;

import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.library.imageloaderfactory.glide.GlideFrameworkFactory;
import com.tushuangxi.smart.tv.library.imageloaderfactory.glide.GlideWrapper;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageConfigProduct;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageFrameworkFactory;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageLoaderWrapper;
import com.tushuangxi.smart.tv.library.imageloaderfactory.picasso.PicassoFramworkFactory;
import com.tushuangxi.smart.tv.library.imageloaderfactory.picasso.PicassoWrapper;
import com.tushuangxi.smart.tv.library.imageloaderfactory.uil.UILFraworkFactory;

import java.io.File;

/**
 * Created by Administrator on 2016/2/29.
 *
 * https://github.com/VankaIn/ImageLoaderFactory
 */
public class ImageLoaderUtils {

    public static int loadTypeUil = 0x01;
    public static int loadTypeGlide = 0x02;
    public static int loadTypePicasso = 0x03;

    public static void init(Context context, File file, boolean debug) {
//            if() 写在配置文件，根据不同的配置创建不同的图片加载器
        // 初始化图片加载器模块
        ImageLoaderWrapper.initDefault(context, file, debug);
        GlideWrapper.init(context);
        PicassoWrapper.init(context);
    }

    public static ImageFrameworkFactory getFramework(int frameworkType) {
        if (frameworkType == loadTypeUil) {
            return new UILFraworkFactory();
        } else if (frameworkType == loadTypeGlide) {
            return new GlideFrameworkFactory();
        } else {
            return new PicassoFramworkFactory();
        }
    }


    public static void loadPic(int loadFrameworkType, String url , ImageView imageView, boolean cleanImageCache) {
        //清楚之前的缓存
        if (cleanImageCache) {
            ImageLoaderUtils.getFramework(loadFrameworkType).createImageLoader().cleanImageCache(url);
        }
        ImageConfigProduct configProduct = ImageLoaderUtils.getFramework(loadFrameworkType).createImageConfig();
        configProduct.setDefaulRes(R.mipmap.icon_pic_loding);
        configProduct.setLoadingRes(R.mipmap.icon_pic_loding);
        configProduct.setFailRes(R.mipmap.icon_pic_errow);
        configProduct.setFadeIn(0);
        ImageLoaderUtils.getFramework(loadFrameworkType).createImageLoader().display(url, imageView, configProduct);
    }
}
