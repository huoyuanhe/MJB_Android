package com.example.googleplayupload;

import android.app.Activity;
import android.app.Application;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEventFailure;
import com.adjust.sdk.AdjustEventSuccess;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.OnEventTrackingFailedListener;
import com.adjust.sdk.OnEventTrackingSucceededListener;

public class MyApplication extends Application {

    private static MyApplication instance;
    private static final String TAG = "MyApplication";
    private AdjustConfig config;
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
        String appToken = "4m1f6zp70f7k";//ad token
//        String environment = AdjustConfig.ENVIRONMENT_SANDBOX;//测试环境
        String environment = AdjustConfig.ENVIRONMENT_PRODUCTION;
        config = new AdjustConfig(this, appToken, environment);
        config.setLogLevel(LogLevel.ERROR);
        // Set event success tracking delegate.
        config.setOnEventTrackingSucceededListener(new OnEventTrackingSucceededListener() {
            @Override
            public void onFinishedEventTrackingSucceeded(AdjustEventSuccess eventSuccessResponseData) {
                //AD埋点事件发送成功log信息输出
                Log.e(TAG, eventSuccessResponseData.toString());
            }
        });
// Set event failure tracking delegate.
        config.setOnEventTrackingFailedListener(new OnEventTrackingFailedListener() {
            @Override
            public void onFinishedEventTrackingFailed(AdjustEventFailure eventFailureResponseData) {
                Log.e(TAG, eventFailureResponseData.toString());
            }
        });

        Adjust.onCreate(config);

        registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());
    }

    /**
     * AD生命周期
     */
    private static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
            Log.e(TAG, "onActivityCreated");
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            Log.e(TAG, "onActivityStarted");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.e(TAG, "onActivityResumed");
            Adjust.onResume();
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.e(TAG, "onActivityPaused");
            Adjust.onPause();
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            Log.e(TAG, "onActivityStopped");

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
            Log.e(TAG, "onActivitySaveInstanceState");
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            Log.e(TAG, "onActivityDestroyed");
        }

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