package com.tushuangxi.smart.tv.lding.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.viewanimator.ViewAnimator;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.rerxmvp.base.BaseActivity;

import butterknife.BindView;

public class QianhaiActivity extends BaseActivity {


    @BindView(R.id.iv1)
     ImageView iv1;
    @BindView(R.id.iv2)
     ImageView iv2;
    @BindView(R.id.iv3)
     ImageView iv3;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_qianhai;
    }

    @Override
    protected View getContentViewLayoutView() {
        return null;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionType() {
        return null;
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected boolean isResultOK() {
        return false;
    }

    @Override
    protected boolean isOpenFloatingAnimationService() {
        return false;
    }

    @Override
    protected boolean isOpenFloatingErWerMaService() {
        return false;
    }

    @Override
    public void initBundleData(Bundle bundle) {

    }

    @Override
    public void initView() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iv1.setVisibility(View.VISIBLE);
            }
        }, 0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iv2.setVisibility(View.VISIBLE);
            }
        }, 250);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iv3.setVisibility(View.VISIBLE);
            }
        }, 600);
        ViewAnimator
                .animate(iv1)
                .scale(0, 1)
                .duration(500)
                .startDelay(0)
                .start();
        ViewAnimator
                .animate(iv2)
                .scale(0, 1)
                .duration(500)
                .startDelay(200)
                .start();
        ViewAnimator
                .animate(iv3)
                .scale(0, 1)
                .duration(500)
                .startDelay(300)
                .start();

//        ViewAnimator
//                .animate(viewById)
//                .translationY(-1000, 0)
//                .alpha(0,1)
//                .andAnimate(viewById)
//                .dp().translationX(-20, 0)
//                .decelerate()
//                .duration(2000)
//                .thenAnimate(viewById)
//                .scale(1f, 0.5f, 1f)
//                .accelerate()
//                .duration(1000)
//                .start();
    }

    @Override
    public void getHttpData(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {

    }
}
