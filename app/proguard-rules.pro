# ---------------------------- 基本Android混淆配置 ----------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# 保留所有重要的Android类
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

# 保留Native方法
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留自定义View相关的get/set方法
-keepclassmembers public class * extends android.view.View {
    void set*(***);
    *** get*();
}

# 保留Parcelable序列化类
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# 保留Serializable序列化的类
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 保留枚举类
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留R文件中的所有静态字段
-keepclassmembers class **.R$* {
    public static <fields>;
}

# ---------------------------- 第三方库特定配置 ----------------------------

# Gson配置
-keep class com.google.gson.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }

# 确保Gson能够正确序列化/反序列化所有类
-keepattributes Signature
-keepattributes *Annotation*
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Adjust SDK配置
-keep public class com.adjust.sdk.** { *; }
-keep class com.google.android.gms.common.ConnectionResult {
    int SUCCESS;
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {
    com.google.android.gms.ads.identifier.AdvertisingIdClient$Info getAdvertisingIdInfo(android.content.Context);
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {
    java.lang.String getId();
    boolean isLimitAdTrackingEnabled();
}
-keep class com.android.installreferrer.** { *; }

# 保留Adjust的WebBridge相关类
-keep class com.adjust.sdk.web.** { *; }

# 保留Google Play服务相关类
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# ---------------------------- 测试相关配置 ----------------------------
# 保留测试框架
-keep class androidx.test.** { *; }
-keep class org.junit.** { *; }
-keep class android.test.** { *; }

# 避免警告
-dontwarn org.junit.**
-dontwarn android.test.**
-dontwarn androidx.test.**
-dontwarn com.google.android.gms.**
-dontwarn com.adjust.sdk.**