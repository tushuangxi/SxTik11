package com.tushuangxi.smart.tv.library.imageloaderfactory.image;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.tushuangxi.smart.tv.library.imageloaderfactory.cofig.BitmapUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 一个用于加载图片到ImageView的工具类，可加载网络图片 使用时先调用init进行初始化
 *
 * 包装了Universal-Image-Loader开源项目
 *
 * String imageUri = "http://site.com/image.png"; // from Web String imageUri =
 * "file:///mnt/sdcard/image.png"; // from SD card String imageUri =
 * "content://media/external/audio/albumart/13"; // from content provider String
 * imageUri = "assets://image.png"; // from assets String imageUri =
 * "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
 *
 * @author Huyf Email:my519820363@gmail.com
 *
 */
public class ImageLoaderWrapper {
    private static ImageLoaderWrapper sDefaultInstance;
    private ImageLoader mDefaultImageLoader;

    private ImageLoaderWrapper(Context context, File cacheDir, boolean debug) {
        context = context.getApplicationContext();
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;

        ImageLoaderConfiguration.Builder imageLoaderConfigurationBuilder = new ImageLoaderConfiguration.Builder(
                context.getApplicationContext())
                .threadPoolSize(5 * getNumCores())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .denyCacheImageMultipleSizesInMemory();

        imageLoaderConfigurationBuilder.diskCacheExtraOptions(screenWidth,
                screenHeight, null);
        imageLoaderConfigurationBuilder.diskCache(new UnlimitedDiscCache(
                cacheDir));
        imageLoaderConfigurationBuilder.diskCacheSize(50 * 1024 * 1024);
        imageLoaderConfigurationBuilder
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator());

        imageLoaderConfigurationBuilder.memoryCache(new FIFOLimitedMemoryCache(
                calculateDefaultMaxSize(context, 4)));
        imageLoaderConfigurationBuilder.memoryCacheSizePercentage(13);
        imageLoaderConfigurationBuilder.memoryCacheExtraOptions(screenWidth,
                screenHeight);

        imageLoaderConfigurationBuilder
                .imageDownloader(new BaseImageDownloader(context));
        imageLoaderConfigurationBuilder
                .imageDecoder(new BaseImageDecoder(debug));
        imageLoaderConfigurationBuilder
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple());
        if (debug) {
            imageLoaderConfigurationBuilder.writeDebugLogs();
        }

        ImageLoader.getInstance().init(imageLoaderConfigurationBuilder.build());
        mDefaultImageLoader = ImageLoader.getInstance();
    }

    public synchronized static ImageLoaderWrapper initDefault(Context context,
                                                              File discCacheDir, boolean debug) {
        if (sDefaultInstance == null) {
            sDefaultInstance = new ImageLoaderWrapper(context, discCacheDir,
                    debug);
        }
        return sDefaultInstance;
    }

    public synchronized static ImageLoaderWrapper getDefault() {
        if (sDefaultInstance == null) {
            throw new RuntimeException(
                    "Must be call initDefault(Context, File) befor!");
        }

        return sDefaultInstance;
    }

    public OnScrollListener newScrollListenerWithTheImageLoader(
            boolean pauseOnScroll, boolean pauseOnFling) {
        return new PauseOnScrollListener(mDefaultImageLoader, pauseOnScroll,
                pauseOnFling);
    }

    public OnScrollListener newScrollListenerWithTheImageLoader(
            boolean pauseOnScroll, boolean pauseOnFling,
            OnScrollListener customListener) {
        return new PauseOnScrollListener(mDefaultImageLoader, pauseOnScroll,
                pauseOnFling, customListener);
    }

    public ImageLoader getImageLoader() {
        return mDefaultImageLoader;
    }

    public void loadImage(String imageUri) {
        loadImage(imageUri, null, null);
    }

    public void loadImage(String imageUri, ImageLoadingListener listener) {
        loadImage(imageUri, null, listener);
    }

    public void loadImage(String imageUri, DisplayConfig config) {
        loadImage(imageUri, config, null);
    }

    public void loadImage(String imageUri, DisplayConfig config,
                          ImageLoadingListener listener) {
        if (config == null) {
            config = new DisplayConfig();
        }

        if (!TextUtils.isEmpty(imageUri)) {
            Uri uri = Uri.parse(imageUri);
            String scheme = uri.getScheme();
            if ("http".equals(scheme)) {
                config.supportDiskCache = true;
            } else {
                config.supportDiskCache = false;
            }
        }

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(config.stubImageRes)
                .showImageForEmptyUri(config.loadFailImageRes)
                .showImageOnFail(config.loadFailImageRes)
                .cacheInMemory(config.supportMemoryCache)
                .cacheOnDisk(config.supportDiskCache)
                .imageScaleType(config.imageScaleType)
                .resetViewBeforeLoading(config.isResetView)
                .displayer(config.displayer).build();

        mDefaultImageLoader.loadImage(imageUri, options, listener);
    }

    public void displayImage(String imageUri, ImageView imageView) {
        displayImage(imageUri, imageView, null, null);
    }

    public void displayImage(String imageUri, ImageView imageView,
                             ImageLoadingListener listener) {
        displayImage(imageUri, imageView, null, listener);
    }

    public void displayImage(String imageUri, ImageView imageView,
                             DisplayConfig config) {
        displayImage(imageUri, imageView, config, null);
    }

    public void displayImage(String imageUri, ImageView imageView,
                             DisplayConfig config, ImageLoadingListener listener) {
        if (config == null) {
            config = new DisplayConfig();
        }

        if (!TextUtils.isEmpty(imageUri)) {
            Uri uri = Uri.parse(imageUri);
            String scheme = uri.getScheme();
            if ("http".equals(scheme)) {
                config.supportDiskCache = true;
            } else {
                config.supportDiskCache = false;
            }
        }

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(config.stubImageRes)
                .showImageForEmptyUri(config.loadFailImageRes)
                .showImageOnFail(config.loadFailImageRes)
                .cacheInMemory(config.supportMemoryCache)
                .cacheOnDisk(config.supportDiskCache)
                .imageScaleType(config.imageScaleType)
                .resetViewBeforeLoading(config.isResetView)
                .bitmapConfig(Config.RGB_565).displayer(config.displayer)
                .considerExifParams(false).build();
        mDefaultImageLoader
                .displayImage(imageUri, imageView, options, listener);
    }

    public void clearDefaultLoaderMemoryCache() {
        mDefaultImageLoader.clearMemoryCache();
    }

    public void clearDefaultLoaderDiscCache() {
        mDefaultImageLoader.clearDiskCache();
    }

    /**
     * 清除单张
     */
    public void clearDefaultLoaderCache(String url){
        removeMemoryFromCache(url);
        removeDiscFromCache(url);
    }

    /**
     * 停止所有下载图片的任务
     */
    public void pause() {
        mDefaultImageLoader.pause();
    }

    /**
     * 恢复被暂停的任务
     */
    public void resume() {
        mDefaultImageLoader.resume();
    }

    /**
     * 取消所有的下载图片的任务
     */
    public void stop() {
        mDefaultImageLoader.stop();
    }

    /**
     * 根据传入的URI搜索所有在内存中缓存的位图 注意：内存缓存可以包含不同尺寸的相同图像
     *
     * @param imageUri
     */
    public List<Bitmap> findMemoryCachedBitmapsForImageUri(String imageUri) {
        if (TextUtils.isEmpty(imageUri)) {
            return null;
        }

        return MemoryCacheUtils.findCachedBitmapsForImageUri(imageUri,
                mDefaultImageLoader.getMemoryCache());
    }

    /**
     * 根据传入的URI删除位图缓存
     *
     * @param imageUri
     */
    public void removeMemoryFromCache(String imageUri) {
        MemoryCacheUtils.removeFromCache(imageUri,
                mDefaultImageLoader.getMemoryCache());
    }

    /**
     * 根据传入的URI搜索所有在磁盘中缓存的位图文件
     *
     * @param imageUri
     */
    public File findDiscCachedBitmapsForImageUri(String imageUri) {
        return DiskCacheUtils.findInCache(imageUri,
                mDefaultImageLoader.getDiskCache());
    }

    /**
     * 根据传入的URI删除位图缓存
     *
     * @param imageUri
     */
    public void removeDiscFromCache(String imageUri) {
        DiskCacheUtils.removeFromCache(imageUri,
                mDefaultImageLoader.getDiskCache());
    }

    // CPU个数
    private int getNumCores() {
        class CpuFilter implements FileFilter {

            @Override
            public boolean accept(File pathname) {
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new CpuFilter());
            return Math.max(1, files.length);
        } catch (Exception e) {
            return 1;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private int calculateDefaultMaxSize(Context context, int weight) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Activity.ACTIVITY_SERVICE);
        boolean largeHeap = (context.getApplicationInfo().flags & ApplicationInfo.FLAG_LARGE_HEAP) != 0;
        int memoryClass = am.getMemoryClass();
        if (largeHeap && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            memoryClass = am.getLargeMemoryClass();
        }

        return 1024 * 1024 * Math.min(12, memoryClass / weight);
    }

    public static class DisplayConfig {
        public boolean supportMemoryCache;
        public boolean supportDiskCache;
        public boolean isResetView;
        public int stubImageRes;
        public int loadFailImageRes;
        private BitmapDisplayer displayer;
        private final ImageScaleType imageScaleType = ImageScaleType.IN_SAMPLE_POWER_OF_2;
        private static final BitmapDisplayer sDefault = new SimpleBitmapDisplayer();
        private static final BitmapDisplayer sDefaultFadein = new FadeInBitmapDisplayer(
                300);
        private static final BitmapDisplayer sDefaultRound = new RoundedBitmapDisplayer(
                10);
        private static final BitmapDisplayer sDefaultCircular = new CircularBitmapDisplayer();

        private DisplayConfig() {
            supportMemoryCache = true;
            supportDiskCache = true;
            isResetView = true;
            stubImageRes = 0;
            loadFailImageRes = 0;
            displayer = new SimpleBitmapDisplayer();
        }

        public static class Builder {
            public Builder() {
            }

            public DisplayConfig build() {
                DisplayConfig config = new DisplayConfig();
                config.displayer = sDefault;
                return config;
            }

            public DisplayConfig buildFadein() {
                DisplayConfig config = new DisplayConfig();
                config.displayer = sDefaultFadein;
                return config;
            }

            public DisplayConfig buildRounded() {
                DisplayConfig config = new DisplayConfig();
                config.displayer = sDefaultRound;
                return config;
            }

            public DisplayConfig buildCircular() {
                DisplayConfig config = new DisplayConfig();
                config.displayer = sDefaultCircular;
                return config;
            }

            public DisplayConfig buildFadein(int fadeInTime) {
                DisplayConfig config = new DisplayConfig();
                config.displayer = new FadeInBitmapDisplayer(fadeInTime);
                return config;
            }

            public DisplayConfig buildRounded(int round) {
                DisplayConfig config = new DisplayConfig();
                config.displayer = new RoundedBitmapDisplayer(round);
                return config;
            }

            public DisplayConfig buildBlur(Context context, int radius) {
                DisplayConfig config = new DisplayConfig();
                config.displayer = new BlurBitmapDisplayer(context, radius);
                return config;
            }

            public DisplayConfig buildGray() {
                DisplayConfig config = new DisplayConfig();
                config.displayer = new GrayBitmapDisplayer();
                return config;
            }

            public DisplayConfig buildAlpha(int alpha) {
                DisplayConfig config = new DisplayConfig();
                config.displayer = new AlphaBitmapDisplayer(alpha);
                return config;
            }
        }
    }

    /**
     * 将图片显示为半透明模糊效果，必须将图片的inPreferredConfig设置为ARGB8888，否则会崩溃
     *
     * Just displays {@link android.graphics.Bitmap} in
     * {@link com.nostra13.universalimageloader.core.imageaware.ImageAware}
     *
     * @author Huyunfeng (my519820363[at]gmail[dot]com)
     * @since 0.0.1
     */
    public final static class BlurBitmapDisplayer implements BitmapDisplayer {
        private Context mContext;
        private int mRadius = 1;

        public BlurBitmapDisplayer(Context context) {
            mContext = context;
        }

        public BlurBitmapDisplayer(Context context, int radius) {
            mContext = context.getApplicationContext();
            mRadius = radius;
        }

        @Override
        public void display(Bitmap bitmap, ImageAware imageAware,
                            LoadedFrom loadedFrom) {
            Bitmap blurBitmap = BitmapUtils.fastblur(mContext, bitmap,
                    Math.max(1, mRadius));
            if (blurBitmap != null && !blurBitmap.isRecycled()) {
                imageAware.setImageBitmap(blurBitmap);
            } else {
                imageAware.setImageBitmap(bitmap);
            }
        }
    }

    /**
     * 将图片显示为灰色效果
     *
     * Just displays {@link android.graphics.Bitmap} in
     * {@link com.nostra13.universalimageloader.core.imageaware.ImageAware}
     *
     * @author Huyunfeng (my519820363[at]gmail[dot]com)
     * @since 0.0.1
     */
    public final static class GrayBitmapDisplayer implements BitmapDisplayer {

        @Override
        public void display(Bitmap bitmap, ImageAware imageAware,
                            LoadedFrom loadedFrom) {
            Bitmap grayBitmap = BitmapUtils.grey(bitmap);
            if (grayBitmap != null && !grayBitmap.isRecycled()) {
                imageAware.setImageBitmap(grayBitmap);
            } else {
                imageAware.setImageBitmap(bitmap);
            }
        }
    }

    public final static class CircularBitmapDisplayer implements
            BitmapDisplayer {

        @Override
        public void display(Bitmap bitmap, ImageAware imageAware,
                            LoadedFrom loadedFrom) {
            Bitmap circularBitmap = BitmapUtils.circular(bitmap);
            if (circularBitmap != null && !circularBitmap.isRecycled()) {
                imageAware.setImageBitmap(circularBitmap);
            } else {
                imageAware.setImageBitmap(bitmap);
            }
        }
    }

    public final static class AlphaBitmapDisplayer implements BitmapDisplayer {
        private int alpha;

        public AlphaBitmapDisplayer(int alpha) {
            this.alpha = alpha;
        }

        @Override
        public void display(Bitmap bitmap, ImageAware imageAware,
                            LoadedFrom loadedFrom) {
            Bitmap grayBitmap = BitmapUtils.alpha(bitmap, alpha);
            if (grayBitmap != null && !grayBitmap.isRecycled()) {
                imageAware.setImageBitmap(grayBitmap);
            } else {
                imageAware.setImageBitmap(bitmap);
            }
        }
    }
}
