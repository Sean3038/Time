package com.example.ffes.time.interactor;

import android.os.Handler;
import android.os.Looper;

import com.example.ffes.time.usecase.UiThreadExecutor;

/**
 * Created by Ffes on 2017/9/23.
 */

public class AndroidUiThreadExecutor implements UiThreadExecutor {
    Handler handler;

    public AndroidUiThreadExecutor(){
        this.handler=new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}
