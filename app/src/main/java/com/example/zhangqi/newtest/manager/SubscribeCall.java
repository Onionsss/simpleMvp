package com.example.zhangqi.newtest.manager;

import com.example.zhangqi.newtest.mvp.view.BaseView;

import rx.Subscriber;

/**
 * Created by zhangqi on 2016/9/23.
 */

public class SubscribeCall<T,V extends BaseView> extends Subscriber<T>{

    private LoadStuatus mLoadStuatus;
    private V mBaseView;
    private SimpleSubscribeImpl<T> mCallBack;

    public SubscribeCall(V baseView, SimpleSubscribeImpl<T> simpleSubscribe) {
        this.mBaseView = baseView;
        this.mLoadStuatus = mBaseView.getLoadView();
        this.mCallBack = simpleSubscribe;
    }

    @Override
    public void onStart() {
        super.onStart();
        mCallBack.onStart();
    }

    @Override
    public void onCompleted() {
        mCallBack.onCompleted();
        mLoadStuatus.updateView(LoadStuatus.STATUS_SUCCESS);
    }

    @Override
    public void onError(Throwable e) {
        mBaseView.error(e);
        mLoadStuatus.updateView(LoadStuatus.STATUS_ERROR);
        mCallBack.onError(e);
    }

    @Override
    public void onNext(T o) {
        mCallBack.onNext(o);
    }

     interface SimpleSubscribe<T>{
        void onStart();

        void onNext(T data);

        void onError(Throwable t);

        void onCompleted();
    }

    public static class SimpleSubscribeImpl<T> implements SimpleSubscribe<T>{

        @Override
        public void onStart() {

        }

        @Override
        public void onNext(T data) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {

        }
    }
}
