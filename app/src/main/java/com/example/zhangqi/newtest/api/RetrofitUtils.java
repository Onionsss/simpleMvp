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
    private static Retrofit sRetrofit;
    private static RetrofitUtils sRetrofitUtil;
    private String mBaseUrl = "http://dota2xufserver.duapp.com";
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
        if(sRetrofit == null){
            sRetrofit = new Retrofit.Builder().baseUrl(mBaseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return sRetrofit.create(Api.class);
    }
    public RetrofitUtils baseUrl(String url){
        mBaseUrl = url;
        return sRetrofitUtil;
    }
}
