package com.example.ffes.time.usecase;

/**
 * Created by Ffes on 2017/9/23.
 */

public abstract class AbstractUseCase {
    protected UiThreadExecutor uiThreadExecutor;
    protected Thread thread;

    public AbstractUseCase(UiThreadExecutor uiThreadExecutor){
        this.uiThreadExecutor=uiThreadExecutor;
    }

    protected void executeAsyne(Runnable runnable){
        this.thread=new Thread(runnable);
        thread.start();
    }
}
