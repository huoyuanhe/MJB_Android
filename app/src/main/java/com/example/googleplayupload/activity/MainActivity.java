package com.example.googleplayupload.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.googleplayupload.MyApplication;
import com.example.googleplayupload.R;
import com.example.googleplayupload.utils.CountDownHelper;

public class MainActivity extends AppCompatActivity {
    private CountDownHelper countDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 创建倒计时实例
        countDown = new CountDownHelper(
                3000, // totalTime: 3秒
                1000,  // interval: 1秒间隔
                new CountDownHelper.OnTickListener() {
                    @Override
                    public void onTick(long remainingMs) {
//                        long seconds = remainingMs / 1000;
                    }
                },
                new CountDownHelper.OnFinishListener() {
                    @Override
                    public void onFinish() {
                        //倒计时结束跳转
                      Intent intent = new Intent(MyApplication.getAppContext(), WebMainActivity.class);
                      startActivity(intent);
                      //关闭当前页面
                      finish();
                    }
                }
        );

        // 开始倒计时
        countDown.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 避免内存泄漏
        if (countDown != null) {
            countDown.cancel();
        }
    }
}