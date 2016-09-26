package com.example.zhangqi.newtest.manager;

import com.example.zhangqi.newtest.mvp.view.BaseView;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 统一处理error  complete页面
 * 并提供回调
 * Created by zhangqi on 2016/9/23.
 */

public class SubscribeCall<T,V extends BaseView> extends Subscriber<T>{

    private boolean isSimpleSubscribe;
    private LoadStuatus mLoadStuatus;
    private V mBaseView;
    private SimpleSubscribeImpl<T> mCallBack;

    /**
     * rxjava 回调
     */
    private Action1<? super T> mOnNext;
    private Action1<Throwable> mOnError;
    private Action0 mOnComplete;
    private Action0 mOnStart;

    public SubscribeCall(V baseView, SimpleSubscribeImpl<T> simpleSubscribe) {
        this.isSimpleSubscribe = true;
        setView(baseView);
        this.mCallBack = simpleSubscribe;
    }

    public SubscribeCall(V baseView,final Action1<? super T> onNext){
        if(onNext == null)
            throw new NullPointerException("onNext空了");
        mOnNext = onNext;
        setView(baseView);
    }

    public SubscribeCall(V baseView,final Action1<? super T> onNext,final Action1<Throwable> onError){
        this(baseView,onNext);
        if(onError == null)
            throw new NullPointerException("onError空了");
        mOnError = onError;
    }

    public SubscribeCall(V baseView,final Action1<? super T> onNext,final Action0 onComplete){
        this(baseView,onNext);
        if(onComplete == null)
            throw new NullPointerException("onComplete空了");
        mOnComplete = onComplete;
    }

    public SubscribeCall(V baseView, final Action1<? super T> onNext, final Action1<Throwable> onError, Action0 onComplete){
        this(baseView,onNext,onError);
        if(onComplete == null)
            throw new NullPointerException("onComplete空了");
        mOnComplete = onComplete;
    }

    public SubscribeCall(V baseView, final Action0 onStart,final Action1<? super T> onNext, final Action1<Throwable> onError, Action0 onComplete){
        this(baseView,onNext,onError,onComplete);
        if(onStart == null)
            throw new NullPointerException("onStart空了");
        mOnStart = onStart;
    }

    private void setView(V baseView){
        this.mBaseView = baseView;
        this.mLoadStuatus = mBaseView.getLoadView();
    }

    @Override
    public void onStart() {
        if(isSimpleSubscribe)
        mCallBack.onStart();
        else{
            if (mOnStart != null)
                mOnStart.call();
             }
    }

    @Override
    public void onCompleted() {
        mLoadStuatus.updateView(LoadStuatus.STATUS_SUCCESS);

        if(isSimpleSubscribe)
            mCallBack.onCompleted();
        else {
            if (mOnComplete != null)
                mOnComplete.call();
        }
    }

    @Override
    public void onError(Throwable e) {
        mBaseView.error(e);
        mLoadStuatus.updateView(LoadStuatus.STATUS_ERROR);

        if(isSimpleSubscribe)
            mCallBack.onError(e);
        else {
            if (mOnError != null)
                mOnError.call(e);
        }
    }

    @Override
    public void onNext(T o) {
        if(isSimpleSubscribe)
        mCallBack.onNext(o);
        else
        mOnNext.call(o);
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
