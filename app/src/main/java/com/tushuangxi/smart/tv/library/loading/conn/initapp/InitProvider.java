package com.tushuangxi.smart.tv.library.loading.conn.initapp;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tushuangxi.smart.tv.lding.utils.ActivityUtils;

/**
 * 通过声明 {@link ContentProvider} 自动完成应用初始化
 */
public class InitProvider  extends ContentProvider {

    @Override
    public boolean onCreate() {
        initApp((Application) getContext().getApplicationContext());
        return true;
    }

    /**
     * 这些初始化操作在主线程会拖延App启动速度
     * 建议在子线程进行
     */
    private void initApp(Application app) {

        //注册生命周期回调
        ActivityUtils.clear();
        app.registerActivityLifecycleCallbacks(new ActivityLifeCycleCallBackIml());
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
