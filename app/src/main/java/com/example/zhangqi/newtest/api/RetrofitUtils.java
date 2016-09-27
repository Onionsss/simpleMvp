package com.example.zhangqi.newtest.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zhangqi on 2016/9/12.
 */
public class RetrofitUtils {

    private static Retrofit sRetrofit;
    private String mBaseUrl = "http://dota2xufserver.duapp.com";
    private RetrofitUtils(){}

    public static class Hide{
        public static RetrofitUtils sRetrofitUtil = new RetrofitUtils();
    }

    public static RetrofitUtils getInstance() {
        return Hide.sRetrofitUtil;
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

}
