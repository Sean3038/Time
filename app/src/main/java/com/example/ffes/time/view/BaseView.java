package com.example.ffes.time.view;

/**
 * Created by Ffes on 2017/9/23.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
