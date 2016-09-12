package com.example.zhangqi.newtest.bean;

/**
 * Created by zhangqi on 2016/9/12.
 */
public class BaseBean<T> {
    private String error;
    private String info;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getNews() {
        return news;
    }

    public void setNews(T news) {
        this.news = news;
    }

    private T news;
}
