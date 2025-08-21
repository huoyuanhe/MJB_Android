package com.example.googleplayupload.utils;

import android.os.CountDownTimer;

/**
 * 启动页倒计时
 */
public class CountDownHelper extends CountDownTimer {

    private final OnTickListener onTickListener;
    private final OnFinishListener onFinishListener;

    public CountDownHelper(long totalTime, long interval,
                           OnTickListener onTickListener,
                           OnFinishListener onFinishListener) {
        super(totalTime, interval);
        this.onTickListener = onTickListener;
        this.onFinishListener = onFinishListener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (onTickListener != null) {
            onTickListener.onTick(millisUntilFinished);
        }
    }

    @Override
    public void onFinish() {
        if (onFinishListener != null) {
            onFinishListener.onFinish();
        }
    }

    // 接口定义
    public interface OnTickListener {
        void onTick(long millisUntilFinished);
    }

    public interface OnFinishListener {
        void onFinish();
    }
}

