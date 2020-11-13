package com.tushuangxi.smart.tv.library.imageloaderfactory.cofig;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.library.imageloaderfactory.ImageLoaderUtils;
import com.tushuangxi.smart.tv.library.imageloaderfactory.image.ImageConfigProduct;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    ImageView iv_uil;

    public int mLastFrameWorkType = 1;
    public final static String url = "http://desk.fd.zol-img.com.cn/g5/M00/05/02/ChMkJ1bD1HCIY1ATAA-BspTxVL8AAKTXwOOeQsAD4HK484.jpg";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_uil = findViewById(R.id.iv_uil);
        loadPic(ImageLoaderUtils.loadTypeUil,url,iv_uil,true);
//        loadPic(ImageLoaderUtils.loadTypeGlide,url,mIvGlide,true);
//        loadPic(ImageLoaderUtils.loadTypePicasso,url,mIvPicasso,true);
    }


    private void loadPic(int loadFrameworkType,String url ,ImageView imageView,boolean cleanImageCache) {
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
