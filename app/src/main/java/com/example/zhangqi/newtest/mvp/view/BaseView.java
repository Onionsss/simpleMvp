package com.example.zhangqi.newtest.mvp.view;

import com.example.zhangqi.newtest.manager.LoadStuatus;

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

    void error(Throwable e);

    LoadStuatus getLoadView();
}
