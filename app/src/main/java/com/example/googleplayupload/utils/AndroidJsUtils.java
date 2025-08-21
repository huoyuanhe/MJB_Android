package com.example.googleplayupload.utils;

import android.app.Activity;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.example.googleplayupload.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class AndroidJsUtils {
    private static final String TAG = "window.Android";
    private final Activity activity;

    public AndroidJsUtils(Activity activity) {
        this.activity = activity;
    }

    @JavascriptInterface
    public void openWebView(String url) {
        //正常都是走这里外跳 天成是走的WebChromeClient方法
        Log.d(TAG, "call: window.Android.openWebView");
        Log.d(TAG, "openWebView:::"+url);
    }

    //h5 android 事件交互方法  通过这个方法检测事件 并发送给AD
    @JavascriptInterface
    public void eventTracker(String eventType, String eventValues){
        Log.d(TAG, "call: window.Android.eventTracker");
        Log.d(TAG, "eventTracker:::"+eventType+"::::"+eventValues);
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> map = gson.fromJson(eventValues, type);
        //判断H5触发的事件名称
        switch (eventType){
            case "first_open":
                PushAdjustEvent(BuildConfig.first_open);
                break;
            case "firstDeposit":
                PushAdjustEvent(BuildConfig.firstDeposit);
                break;
            case "firstDepositArrival":
                PushAdjustEvent(BuildConfig.firstDepositArrival);
                break;
            case "firstOpen":
                PushAdjustEvent(BuildConfig.firstOpen);
                break;
            case "login":
                PushAdjustEvent(BuildConfig.login);
                break;
            case "register":
                PushAdjustEvent(BuildConfig.register);
                break;
        }
    }

    //发送事件到AD
    private void PushAdjustEvent(String str) {
        Log.e(TAG, str);
        AdjustEvent adjustEvent = new AdjustEvent(str);
        Adjust.trackEvent(adjustEvent);
    }
}
