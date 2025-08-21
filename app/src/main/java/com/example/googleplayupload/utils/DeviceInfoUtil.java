package com.example.googleplayupload.utils;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import java.util.Locale;
import java.util.TimeZone;

public class DeviceInfoUtil {

    // 获取时区 ID，例如 "Asia/Shanghai"
    private static String getTimeZone() {
        return TimeZone.getDefault().getID();
    }

    // 获取系统语言，例如 "zh"
    private static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    // 获取国家，例如 "CN"
    private static String getCountry() {
        return Locale.getDefault().getCountry();
    }

    // 获取 Android_ID（设备唯一标识的一种，但可能为空或在某些设备上会变）
    private static String getAndroidId(Context context) {
        return Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.ANDROID_ID
        );
    }

    public static String getHeader(Context context) {
        String tz = DeviceInfoUtil.getTimeZone();
        String lang = DeviceInfoUtil.getLanguage();
        String country = DeviceInfoUtil.getCountry();
        String aid = DeviceInfoUtil.getAndroidId(context);
        String headerString = "time=" + tz + "&lang=" + lang + "&country=" + country + "&aid=" + aid;
        Log.e("aaa", headerString);
        String encryptedData = AESUtil.encrypt(headerString);
        Log.e("aaa", encryptedData);
        String decrypt = AESUtil.decrypt(encryptedData);
        Log.e("aaa", decrypt);
        return encryptedData;
    }
}

