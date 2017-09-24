package com.example.ffes.time.usecase;

import com.example.ffes.time.service.CountService;

/**
 * Created by Ffes on 2017/9/23.
 */

public class StopCountUseCaseImpl extends AbstractUseCase implements StopCountUseCase{
    CountService countService;
    public StopCountUseCaseImpl(UiThreadExecutor uiThreadExecutor) {
        super(uiThreadExecutor);
        countService=CountService.getService();
    }

    @Override
    public void execute() {
        executeAsyne(this);
    }

    @Override
    public void run() {
        countService.stopCount();
    }
}
