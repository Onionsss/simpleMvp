package com.example.zhangqi.newtest.ui;

import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.zhangqi.newtest.mvp.presenter.BasePresenter;
import com.example.zhangqi.newtest.mvp.view.impl.BaseViewImpl;

import butterknife.ButterKnife;

/**
 * Created by zhangqi on 2016/9/12.
 */
public abstract class BaseActivity<T extends BasePresenter,D> extends AppCompatActivity implements BaseViewImpl<D>{

    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 绑定Presenter
         */
        attachPre();
        View view = initView(LayoutInflater.from(this));
        setContentView(view);
        ButterKnife.bind(this);
        findView(view);
        loadData();
    }

    protected abstract View initView(LayoutInflater from);

    public abstract void findView(View view);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null)
            mPresenter.detachView();
        ButterKnife.unbind(this);
    }

    /**
     * error的统一处理
     * @param error
     */
    public void error(Throwable error){
        if(error instanceof NetworkErrorException){
            Toast.makeText(this,"网络错误", Toast.LENGTH_SHORT).show();
        }
    }

}
