package com.example.zhangqi.newtest.manager;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.zhangqi.newtest.R;
import com.example.zhangqi.newtest.mvp.view.BaseView;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xblydxj on 2016/7/18/018.
 */
public class LoadStuatus extends FrameLayout {
    private static final String TAG = "TAG";
    BaseView nView;
    @Bind(R.id.error)
    View mErrorView;
    @Bind(R.id.loading)
    View mLoadingView;

    View successView;
    //加载状态
    public static final int STATUS_LOADING = 100;
    //成功状态
    public static final int STATUS_SUCCESS = 101;
    //失败状态
    public static final int STATUS_ERROR = 102;

    public LoadStuatus(Context context) {
        this(context, null);
    }

    public LoadStuatus(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadStuatus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void addSuccessView(View view,BaseView view1){
        successView = view;
        addView(successView);
        successView.setVisibility(GONE);

        this.nView = view1;
        RxView.clicks(mErrorView).throttleFirst(5, TimeUnit.SECONDS)
                .subscribe(v -> {
                    if(nView == null)
                        throw new NoBindViewException("没有绑定View");
                    nView.loadData();
                });
    }

    public void init() {
        View.inflate(getContext(), R.layout.status, this);
        ButterKnife.bind(this);
    }

    public void updateView(int currentStatus) {
        successView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mLoadingView.setVisibility(GONE);
        switch (currentStatus) {
            case STATUS_ERROR:
                mErrorView.setVisibility(VISIBLE);
                break;
            case STATUS_LOADING:
                mLoadingView.setVisibility(VISIBLE);
                break;
            case STATUS_SUCCESS:
                successView.setVisibility(VISIBLE);
                break;
        }
    }

    public class NoBindViewException extends RuntimeException{
        public NoBindViewException(String info){
            super(info);
        }
    }

    /**
     * error的统一处理
     * @param error
     */
    public void error(Throwable error){
        Log.d(TAG, "error: "+error.toString());
        if(error instanceof NetworkErrorException){
            Toast.makeText(getContext(),"网络错误", Toast.LENGTH_SHORT).show();
        }
    }
}
