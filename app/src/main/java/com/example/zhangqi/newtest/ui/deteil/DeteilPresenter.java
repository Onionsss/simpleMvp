package com.example.zhangqi.newtest.ui.deteil;

import android.content.Context;

import com.example.zhangqi.newtest.api.RetrofitUtils;
import com.example.zhangqi.newtest.manager.SubscribeCall;
import com.example.zhangqi.newtest.manager.TransformUtils;
import com.example.zhangqi.newtest.mvp.presenter.impl.BasePresenterImpl;
import com.example.zhangqi.newtest.ui.Home.HomeActivity;

import java.util.Map;

/**
 * Created by zhangqi on 2016/9/23.
 */

public class DeteilPresenter extends BasePresenterImpl<DeteilActivity>{

    public DeteilPresenter(Context context) {
        super(context);
    }

    @Override
    public void loadData(Map<String, String> map) {
        RetrofitUtils.getinstance(mContext).buildNews().getNewsDetail(map.get(HomeActivity.DATE),HomeActivity.DATE)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(new SubscribeCall<String>(mBaseView.getLoadView(),new SubscribeCall.SimpleSubscribeImpl<String>(){
                    @Override
                    public void onNext(String data) {
                        super.onNext(data);
                        mBaseView.showData(data);
                    }
                }));
    }
}
