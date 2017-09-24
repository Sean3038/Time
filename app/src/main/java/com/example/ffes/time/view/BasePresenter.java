package com.example.ffes.time.view;

/**
 * Created by Ffes on 2017/9/23.
 */

public interface BasePresenter <T extends BaseView>{
    void bind(T view);
    void unbind();
}
