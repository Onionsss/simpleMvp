package com.example.zhangqi.newtest.ui.Home;

import android.content.Context;

import com.example.zhangqi.newtest.api.RetrofitUtils;
import com.example.zhangqi.newtest.bean.NewsList;
import com.example.zhangqi.newtest.manager.SubscribeCall;
import com.example.zhangqi.newtest.manager.TransformUtils;
import com.example.zhangqi.newtest.mvp.presenter.impl.BasePresenterImpl;

import java.util.Map;

import rx.Subscription;

/**
 * Created by zhangqi on 2016/9/12.
 */
public class HomePresenter extends BasePresenterImpl<HomeActivity>{

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    public void loadData(Map<String,String> map) {
        Subscription subscribe = RetrofitUtils.getinstance(mContext).buildNews().getNews()
               .compose(TransformUtils.defaultSchedulers())
                .subscribe(new SubscribeCall<NewsList>(mBaseView.getLoadView(), new SubscribeCall.SimpleSubscribeImpl<NewsList>() {
                    @Override
                    public void onNext(NewsList bean) {
                        super.onNext(bean);
                        mBaseView.showData(bean.getNews());
                    }
                }));
        addSubscription(subscribe);
    }
}
