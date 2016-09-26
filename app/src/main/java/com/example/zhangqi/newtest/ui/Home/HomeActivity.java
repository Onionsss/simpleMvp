package com.example.zhangqi.newtest.ui.Home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.zhangqi.newtest.R;
import com.example.zhangqi.newtest.adapter.NewsListAdapter;
import com.example.zhangqi.newtest.bean.NewsList;
import com.example.zhangqi.newtest.manager.LoadStuatus;
import com.example.zhangqi.newtest.ui.BaseActivity;
import com.example.zhangqi.newtest.ui.deteil.DeteilActivity;

import java.util.List;

import butterknife.Bind;

/**
 * Created by zhangqi on 2016/9/12.
 */
public class HomeActivity extends BaseActivity<HomePresenter, List<NewsList.NewsEntity>> {
    public static final String DATE = "date";
    public static final String UID = "uid";
    @Bind(R.id.recy)
    RecyclerView mRecy;
    private NewsListAdapter mNewsListAdapter;

    @Override
    public View initView(LayoutInflater inflater) {
        /**
         * UI框架
         */
        mLoadStuatus = new LoadStuatus(this);
        /**
         * 实现成功页面的View
         */
        mLoadStuatus.addSuccessView(inflater.inflate(R.layout.activity_home, null),this);
        /**
         * 绑定BaseView来实现页面点击加载
         */
        return mLoadStuatus;
    }

    @Override
    public void findView(View view) {
        mRecy.setLayoutManager(new LinearLayoutManager(this));
        mNewsListAdapter = new NewsListAdapter(this);
        mNewsListAdapter.setOnItemClickListener(news -> {
            Intent intent = new Intent(this, DeteilActivity.class);
            intent.putExtra(DATE,news.getDate());
            intent.putExtra(UID,news.getNid());
            startActivity(intent);
        });
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
        mPresenter.loadData(null);
    }
}
