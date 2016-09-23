package com.example.zhangqi.newtest.api;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zhangqi on 2016/9/12.
 */
public class RetrofitUtils {
    private Context mContext;
    private static Retrofit mRetrofit;
    private static RetrofitUtils sRetrofitUtil;

    private RetrofitUtils(Context context){
        mContext = context;
    }

    public static RetrofitUtils getinstance(Context context){
        if(sRetrofitUtil == null){
            sRetrofitUtil = new RetrofitUtils(context);
        }
        return sRetrofitUtil;
    }

    public Api buildNews(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder().baseUrl("http://dota2xufserver.duapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit.create(Api.class);
    }

}
