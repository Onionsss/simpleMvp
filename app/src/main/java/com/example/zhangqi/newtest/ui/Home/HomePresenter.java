package com.example.zhangqi.newtest.ui.Home;

import android.content.Context;

import com.example.zhangqi.newtest.api.RetrofitUtils;
import com.example.zhangqi.newtest.manager.LoadStuatus;
import com.example.zhangqi.newtest.mvp.presenter.impl.BasePresenterImpl;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangqi on 2016/9/12.
 */
public class HomePresenter extends BasePresenterImpl<HomeActivity>{

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    public void loadData() {
        Subscription subscribe = RetrofitUtils.getinstance(mContext).buildNews().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> mBaseView.showData(bean.getNews()), //onNext() 去更新页面
                        error -> {
                            mBaseView.error(error); // 请求网络失败 在BaseActivity定义了公共的处理方法
                            mBaseView.updateState(LoadStuatus.STATUS_ERROR); //更新页面 显示失败的页面
                        },
                        () -> mBaseView.updateState(LoadStuatus.STATUS_SUCCESS));// onComplete 完成页面更新,显示成功的页面
        addSubscription(subscribe);
    }
}
