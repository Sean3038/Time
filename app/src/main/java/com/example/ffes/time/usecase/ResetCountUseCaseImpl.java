package com.example.ffes.time.usecase;

import com.example.ffes.time.service.CountService;

/**
 * Created by Ffes on 2017/9/23.
 */

public class ResetCountUseCaseImpl extends AbstractUseCase implements ResetCountUseCase{

    CountService service;

    public ResetCountUseCaseImpl(UiThreadExecutor uiThreadExecutor) {
        super(uiThreadExecutor);
        service=CountService.getService();
    }

    @Override
    public void execute() {
        executeAsyne(this);
    }

    @Override
    public void run() {
        service.resetCount();
    }
}
