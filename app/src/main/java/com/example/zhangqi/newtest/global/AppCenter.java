package com.example.zhangqi.newtest.global;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangqi on 2016/9/26.
 */

public class AppCenter extends Application{

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
