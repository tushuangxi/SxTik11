package com.tushuangxi.smart.tv.lding.rerxmvp.base;

import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.AppBarLayout;
import com.tushuangxi.smart.tv.R;
import butterknife.BindView;

/**
 * 带Toolbar的基础Activity
 */
public abstract class ToolBarActivity<T extends BasePresenter> extends BaseActivity {
    protected ActionBar actionBar;
    protected boolean isToolBarHiding = false;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.app_bar)
    protected AppBarLayout appBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();
    }
    /**
     * 设置 home icon 是否可见
     * @return
     */
    protected boolean canBack(){
        return true;
    }

    protected void initToolBar(){
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(canBack());
        }
    }

    protected void hideOrShowToolBar() {
        appBar.animate()
                .translationY(isToolBarHiding ? 0 : -appBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        isToolBarHiding = !isToolBarHiding;
    }
}
