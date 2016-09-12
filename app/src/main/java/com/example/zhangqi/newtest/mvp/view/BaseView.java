package com.example.zhangqi.newtest.mvp.view;

/**
 * Created by zhangqi on 2016/9/12.
 */
public interface BaseView {

    /**
     * 初始化数据
     */
    void loadData();

    /**
     * 更新View的状态
     * @param state
     */
    void updateState(int state);
}
