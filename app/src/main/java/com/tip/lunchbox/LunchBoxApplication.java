package com.tip.lunchbox;

import android.app.Application;
import android.content.Context;

public class LunchBoxApplication extends Application {

    static LunchBoxApplication instance;

    public static LunchBoxApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
