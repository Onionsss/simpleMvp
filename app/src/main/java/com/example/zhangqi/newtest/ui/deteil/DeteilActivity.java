package com.example.zhangqi.newtest.ui.deteil;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.zhangqi.newtest.R;
import com.example.zhangqi.newtest.manager.LoadStuatus;
import com.example.zhangqi.newtest.ui.BaseActivity;
import com.example.zhangqi.newtest.ui.Home.HomeActivity;

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
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setSupportZoom(false);
        settings.setPluginState(WebSettings.PluginState.ON);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    mWebview_progressbar.setVisibility(View.GONE);
                } else {
                    mWebview_progressbar.setVisibility(View.VISIBLE);
                    mWebview_progressbar.setProgress(newProgress);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        mWebView.loadDataWithBaseURL(null, list, "text/html", "UTF-8", null);
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
