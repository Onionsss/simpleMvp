package com.example.zhangqi.newtest.ui.Home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.zhangqi.newtest.R;
import com.example.zhangqi.newtest.adapter.NewsListAdapter;
import com.example.zhangqi.newtest.bean.NewsList;
import com.example.zhangqi.newtest.manager.LoadStuatus;
import com.example.zhangqi.newtest.ui.BaseActivity;

import java.util.List;

import butterknife.Bind;

/**
 * Created by zhangqi on 2016/9/12.
 */
public class HomeActivity extends BaseActivity<HomePresenter, NewsList.NewsEntity> {

    @Bind(R.id.recy)
    RecyclerView mRecy;
    private NewsListAdapter mNewsListAdapter;
    private LoadStuatus mLoadStuatus;

    @Override
    public View initView(LayoutInflater inflater) {
        /**
         * UI框架
         */
        mLoadStuatus = new LoadStuatus(this);
        /**
         * 实现成功页面的View
         */
        mLoadStuatus.addSuccessView(inflater.inflate(R.layout.activity_home, null));
        /**
         * 绑定BaseView来实现页面点击加载
         */
        mLoadStuatus.bindView(this);
        return mLoadStuatus;
    }

    @Override
    public void findView(View view) {
        mRecy.setLayoutManager(new LinearLayoutManager(this));
        mNewsListAdapter = new NewsListAdapter(this);
        mRecy.setAdapter(mNewsListAdapter);
    }

    @Override
    public void attachPre() {
        mPresenter = new HomePresenter(this);
        mPresenter.attachView(this);
    }

    @Override
    public void showData(List<NewsList.NewsEntity> list) {
        mNewsListAdapter.flush(list);
    }

    @Override
    public void loadData() {
        mPresenter.loadData();
    }

    /**
     * 更新页面
     */
    @Override
    public void updateState(int state) {
        mLoadStuatus.updateView(state);
    }
}
