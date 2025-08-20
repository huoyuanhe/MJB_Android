package com.example.googleplayupload;

import android.app.Application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {

    private static MyApplication instance;
    private static final String TAG = "MyApplication";

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initializeApp();
    }

    private void initializeApp() {
        // 初始化第三方SDK
        initializeThirdPartyLibraries();

    }

    private void initializeThirdPartyLibraries() {

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // 清理缓存、释放资源
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory level: " + level);
        // 根据内存级别处理
    }
}