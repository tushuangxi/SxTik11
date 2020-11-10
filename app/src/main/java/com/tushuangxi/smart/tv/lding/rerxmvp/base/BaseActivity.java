package com.tushuangxi.smart.tv.lding.rerxmvp.base;

import android.view.MenuItem;

/**
 *  desc  基于mvp模式的基类activity
 */
public abstract class BaseActivity extends BaseAppCompatActivity {

    String TAG = BaseActivity.class.getSimpleName()+"....";
    //home 右侧标题 Action Bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //在Action Bar的最左边，就是Home icon和标题的区域
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
