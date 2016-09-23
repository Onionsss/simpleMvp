package com.example.zhangqi.newtest.mvp.presenter.impl;

import android.content.Context;

import com.example.zhangqi.newtest.mvp.presenter.BasePresenter;
import com.example.zhangqi.newtest.mvp.view.BaseView;

import java.util.Map;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangqi on 2016/9/12.
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V>{

    public Context mContext;

    public V mBaseView;

    private CompositeSubscription mCompositeSubscription;

    public BasePresenterImpl(Context context){
        mContext = context;
    }

    @Override
    public void attachView(V view) {
        mBaseView = view;
    }

    @Override
    public void detachView() {
        this.mBaseView = null;
        onUnsubscribe(); //解绑
    }

    /**
     * 加载数据
     */
    public abstract void loadData(Map<String,String> map);

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscriber);
    }
}
