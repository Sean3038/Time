package com.example.ffes.time.view;

import com.example.ffes.time.data.Time;

/**
 * Created by Ffes on 2017/9/23.
 */

public interface CountContract {
    interface View extends BaseView<Presenter>{
        void setTotalTime(Time time);
        void setCurrentTime(Time time);
        void showStartPane();
        void showStopSinglePane();
        void showStartResetPane();
        void showStartNotify();
        void hideStartNotify();
        void showCountNotify();
        void hideCountNotify();
    }
    interface Presenter extends BasePresenter<View> {
        void start();
        void stop();
        void singleCount();
        void reset();
    }
}
