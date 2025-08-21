plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.googleplayupload"
    compileSdk = 35
// 启用 BuildConfig 功能
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        applicationId = "com.example.googleplayupload"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // BuildConfig字段
        //AD 识别码 事件码
        buildConfigField("String", "AD_TOKEN", "\"4m1f6zp70f7k\"")
        buildConfigField("String", "first_open", "\"ly9tv0\"")
        buildConfigField("String", "firstDeposit", "\"mpxeoy\"")
        buildConfigField("String", "firstDepositArrival", "\"xhbeqh\"")
        buildConfigField("String", "firstOpen", "\"547tmn\"")
        buildConfigField("String", "login", "\"dy30lh\"")
        buildConfigField("String", "register", "\"3h39n9\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //gson
    implementation("com.google.code.gson:gson:2.8.0")
    //adjust
    implementation("com.adjust.sdk:adjust-android:4.33.5")
    implementation("com.android.installreferrer:installreferrer:2.2")
    implementation("com.adjust.sdk:adjust-android-webbridge:4.33.5")
    implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")
    implementation("com.google.android.gms:play-services-appset:16.0.2")
}