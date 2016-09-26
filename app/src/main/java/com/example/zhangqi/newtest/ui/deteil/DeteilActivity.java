package com.example.zhangqi.newtest.ui.deteil;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.zhangqi.newtest.R;
import com.example.zhangqi.newtest.manager.LoadStuatus;
import com.example.zhangqi.newtest.mvp.baseactivity.BaseActivity;
import com.example.zhangqi.newtest.ui.Home.HomeActivity;
import com.example.zhangqi.newtest.utils.UtilsCollection;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by zhangqi on 2016/9/23.
 */

public class DeteilActivity extends BaseActivity<DeteilPresenter, String> {

    @Bind(R.id.webview_progressbar)
    ProgressBar mWebview_progressbar;
    @Bind(R.id.webview)
    WebView mWebView;

    private String mDate;
    private String mUid;

    @Override
    protected View initView(LayoutInflater from) {

        mLoadStuatus = new LoadStuatus(this);
        /**
         * 实现成功页面的View
         *
         * 绑定BaseView来实现页面点击加载
         */
        mLoadStuatus.addSuccessView(from.inflate(R.layout.layout_base_webview, null),this);

        return mLoadStuatus;
    }

    @Override
    public void findView(View view) {
    }

    @Override
    public void attachPre() {
        mPresenter = new DeteilPresenter(this);
        mPresenter.attachView(this);
    }

    @Override
    public void showData(String list) {
        UtilsCollection.loadWebView(mWebview_progressbar,mWebView,list);
    }

    @Override
    public void loadData() {
        Intent intent = getIntent();
        mDate = intent.getStringExtra(HomeActivity.DATE);
        mUid = intent.getStringExtra(HomeActivity.UID);
        Map<String, String> map = new HashMap<>();
        map.put(HomeActivity.DATE, mDate);
        map.put(HomeActivity.UID, mUid);
        mPresenter.loadData(map);
    }

}
