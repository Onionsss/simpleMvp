package com.example.zhangqi.newtest.mvp.view.impl;

import com.example.zhangqi.newtest.mvp.view.BaseView;

/**
 * Created by zhangqi on 2016/9/12.
 */
public interface BaseViewImpl<D> extends BaseView{

    void attachPre();

    void showData(D data);

}
