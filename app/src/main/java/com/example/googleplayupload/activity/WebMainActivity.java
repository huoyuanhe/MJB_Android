package com.example.googleplayupload.activity;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.googleplayupload.MyApplication;
import com.example.googleplayupload.R;
import com.example.googleplayupload.utils.AndroidJsUtils;

public class WebMainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        context = this;
        //初始化布局
        WebView webView = findViewById(R.id.webview);
        initWebView(webView);
    }

    private void initWebView(WebView webView) {
//        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.addJavascriptInterface(new AndroidJsUtils(this), "Android");
//        webView.loadUrl("file:///android_asset/aaa/index.html");//加载A面项目文件
        webView.loadUrl("https://mex1190.33mex44.com/m/register");//测试B面

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                WebView tempWebView = new WebView(context);
                tempWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        openSystemBrowser(request.getUrl());
                        return true;
                    }

                    @Override
                    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                        sslErrorHandler.proceed();
                    }
                });
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(tempWebView);
                resultMsg.sendToTarget();
                return true;
            }
        });

    }

    private void openSystemBrowser(Uri uri) {
        Intent intent;
        try {
            intent = Intent.parseUri(uri.toString(), Intent.URI_INTENT_SCHEME);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setComponent(null);
            context.startActivity(intent);
        } catch (Exception e) {
            // do some error handle
            Log.e("openSystemBrowser failure", e.toString());
        }
    }
}

