package com.example.zhangqi.newtest.ui;

import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.MalformedJsonException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.zhangqi.newtest.manager.LoadStuatus;
import com.example.zhangqi.newtest.mvp.presenter.BasePresenter;
import com.example.zhangqi.newtest.mvp.view.impl.BaseViewImpl;

import java.net.UnknownHostException;

import butterknife.ButterKnife;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by zhangqi on 2016/9/12.
 */
public abstract class BaseActivity<T extends BasePresenter,D> extends AppCompatActivity implements BaseViewImpl<D>{

    public LoadStuatus mLoadStuatus;
    public T mPresenter;
    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 绑定Presenter
         */
        attachPre();
        mView = initView(LayoutInflater.from(this));
        setContentView(mView);
        ButterKnife.bind(this);
        findView(mView);
        loadData();
    }

    protected abstract View initView(LayoutInflater from);

    @Override
    public LoadStuatus getLoadView(){
        return (LoadStuatus)mView;
    }

    public abstract void findView(View view);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null)
            mPresenter.detachView();
        ButterKnife.unbind(this);
    }

    /**
     * 更新页面
     */
    @Override
    public void updateState(int state) {
        mLoadStuatus.updateView(state);
    }

    /**
     * error的统一处理
     * @param error
     */
    @Override
    public void error(Throwable error){
        if(error instanceof NetworkErrorException){
            Toast.makeText(this,"网络错误", Toast.LENGTH_SHORT).show();
        }
        if(error instanceof HttpException){
            Toast.makeText(this,"服务器爆炸!!!", Toast.LENGTH_SHORT).show();
        }
        if(error instanceof UnknownHostException){
            Toast.makeText(this,"网络错误!", Toast.LENGTH_SHORT).show();
        }
        if((MalformedJsonException)error instanceof MalformedJsonException){
            Toast.makeText(this,"Gson!", Toast.LENGTH_SHORT).show();
        }
    }
}
