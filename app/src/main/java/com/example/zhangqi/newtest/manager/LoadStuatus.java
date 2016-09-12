package com.example.zhangqi.newtest.manager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.zhangqi.newtest.R;
import com.example.zhangqi.newtest.mvp.view.BaseView;

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

    public void addSuccessView(View view){
        successView = view;
        addView(successView);
        successView.setVisibility(GONE);
    }
    public void bindView(BaseView view) {
        this.nView = view;
        mErrorView.setOnClickListener(v -> {
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
        switch (currentStatus) {
            case STATUS_ERROR:
                successView.setVisibility(GONE);
                mErrorView.setVisibility(VISIBLE);
                mLoadingView.setVisibility(GONE);
                break;
            case STATUS_LOADING:
                successView.setVisibility(GONE);
                mErrorView.setVisibility(GONE);
                mLoadingView.setVisibility(VISIBLE);
                break;
            case STATUS_SUCCESS:
                successView.setVisibility(VISIBLE);
                mErrorView.setVisibility(GONE);
                mLoadingView.setVisibility(GONE);
                break;
        }
    }

    public class NoBindViewException extends RuntimeException{
        public NoBindViewException(String info){
            super(info);
        }
    }
}
