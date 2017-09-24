package com.example.ffes.time.usecase;

import com.example.ffes.time.service.CountService;

/**
 * Created by Ffes on 2017/9/24.
 */

public class SingleCountUseCaseImpl extends AbstractUseCase implements SingleCountUseCase {

    CountService service;

    public SingleCountUseCaseImpl(UiThreadExecutor uiThreadExecutor) {
        super(uiThreadExecutor);
        service=CountService.getService();
    }

    @Override
    public void execute() {
        executeAsyne(this);
    }

    @Override
    public void run() {
        service.singleCount();
    }
}
