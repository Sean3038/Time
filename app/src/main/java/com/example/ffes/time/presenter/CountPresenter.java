package com.example.ffes.time.presenter;

import com.example.ffes.time.data.Record;
import com.example.ffes.time.data.Time;
import com.example.ffes.time.interactor.AndroidUiThreadExecutor;
import com.example.ffes.time.service.CountService;
import com.example.ffes.time.usecase.ResetCountUseCase;
import com.example.ffes.time.usecase.ResetCountUseCaseImpl;
import com.example.ffes.time.usecase.SingleCountUseCase;
import com.example.ffes.time.usecase.SingleCountUseCaseImpl;
import com.example.ffes.time.usecase.StartCountUseCase;
import com.example.ffes.time.usecase.StartCountUseCaseImpl;
import com.example.ffes.time.usecase.StopCountUseCase;
import com.example.ffes.time.usecase.StopCountUseCaseImpl;
import com.example.ffes.time.view.CountContract;
import com.example.ffes.time.view.RecordDataModel;

/**
 * Created by Ffes on 2017/9/23.
 */

public class CountPresenter implements CountContract.Presenter ,CountService.OnChangeTime{
    CountContract.View view;
    RecordDataModel dataModel;

    StartCountUseCase startCountUseCase=new StartCountUseCaseImpl(new AndroidUiThreadExecutor());
    StopCountUseCase stopCountUseCase=new StopCountUseCaseImpl(new AndroidUiThreadExecutor());
    ResetCountUseCase resetCountUseCase=new ResetCountUseCaseImpl(new AndroidUiThreadExecutor());
    SingleCountUseCase singleCountUseCase=new SingleCountUseCaseImpl(new AndroidUiThreadExecutor());


    CountService service;
    public CountPresenter(CountContract.View view, RecordDataModel dataModel){
        this.view=view;
        this.view.setPresenter(this);
        service=CountService.getService();
        service.setOnChangeTime(this);
        this.dataModel=dataModel;
    }

    @Override
    public void start() {
        view.hideStartNotify();
        view.showStopSinglePane();
        if(dataModel.getItemCount()==0){
            view.showCountNotify();
        }
        startCountUseCase.execute();

    }

    @Override
    public void stop() {
        view.hideCountNotify();
        view.showStartResetPane();
        if(dataModel.getItemCount()==0){
            view.showStartNotify();
        }
        stopCountUseCase.execute();
    }

    @Override
    public void singleCount() {
        view.hideCountNotify();
        singleCountUseCase.execute();
    }

    @Override
    public void reset() {
        view.showStartPane();
        resetCountUseCase.execute();
        dataModel.clear();
        view.showStartNotify();
    }

    @Override
    public void bind(CountContract.View view) {
        this.view=view;
        this.view.showStartNotify();
    }

    @Override
    public void unbind() {
        this.view=null;
    }

    @Override
    public void onChange(Time time) {
        view.setTotalTime(time);
    }

    @Override
    public void onSingleChange(Time time) {
        view.setCurrentTime(time);
    }

    @Override
    public void onUpdateRecord(Record record) {
        dataModel.update(record.getLoop()-1,record);
    }

    @Override
    public void onRecord(Record record) {
        dataModel.add(record);
    }
}
