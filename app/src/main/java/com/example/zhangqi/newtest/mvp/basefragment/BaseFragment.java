package com.example.zhangqi.newtest.mvp.basefragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangqi.newtest.manager.LoadStuatus;
import com.example.zhangqi.newtest.mvp.presenter.BasePresenter;
import com.example.zhangqi.newtest.mvp.view.impl.BaseViewImpl;
import com.example.zhangqi.newtest.utils.UtilsCollection;

import butterknife.ButterKnife;

/**
 * Created by zhangqi on 2016/9/26.
 */

public abstract class BaseFragment<T extends BasePresenter,D> extends Fragment implements BaseViewImpl<D> {


    public LoadStuatus mLoadStuatus;
    public T mPresenter;
    private View mView;
    protected Activity mMActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        attachPre();
        mView = initView(LayoutInflater.from(mMActivity));
        ButterKnife.bind(this,mView);
        findView(mView);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    protected abstract View initView(LayoutInflater from);

    @Override
    public LoadStuatus getLoadView(){
        return (LoadStuatus)mView;
    }

    /**
     * 用于操作控件初始化配置
     * @param view
     */
    public abstract void findView(View view);

    @Override
    public void onDestroy() {
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
        UtilsCollection.errorutil(error);
    }
}
