package com.example.zhangqi.newtest.mvp.view.impl;

import com.example.zhangqi.newtest.mvp.view.BaseView;

/**
 * Created by zhangqi on 2016/9/12.
 */
public interface BaseViewImpl<D> extends BaseView{
    /**
     * 绑定presenter
     * presenter绑定View
     *
     * mPresenter = new Prensenter();
     * mpresenter.attachView(this);
     */
    void attachPre();

    void showData(D data);
}
