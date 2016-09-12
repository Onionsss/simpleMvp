package com.example.zhangqi.newtest.mvp.presenter;

/**
 * Created by zhangqi on 2016/9/12.
 */
public interface BasePresenter<V> {
    /**
     * 绑定View
     * @param view
     */
    void attachView(V view);

    /**
     * 解绑View
     */
    void detachView();
}
