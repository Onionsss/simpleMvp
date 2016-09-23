package com.example.zhangqi.newtest.api;

import com.example.zhangqi.newtest.bean.NewsList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zhangqi on 2016/9/12.
 */
public interface Api {

    @GET("/api/v1.0/news/refresh")
    Observable<NewsList> getNews();
    @GET("/api/v1.0/newsdetail/{date}/{nid}")
    Observable<String> getNewsDetail(@Path("date") String date, @Path("nid") String nid);
}
