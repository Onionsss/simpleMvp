package com.example.zhangqi.newtest.manager;

import rx.Subscriber;

/**
 * Created by zhangqi on 2016/9/23.
 */

public class SubscribeCall<T> extends Subscriber<T>{

    private LoadStuatus mLoadStuatus;
    private SimpleSubscribeImpl<T> mCallBack;

    public SubscribeCall(LoadStuatus loadStuatus, SimpleSubscribeImpl<T> simpleSubscribe) {
        mLoadStuatus = loadStuatus;
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
        mLoadStuatus.error(e);
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
