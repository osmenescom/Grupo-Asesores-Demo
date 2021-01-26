package com.asgeirr.grupoasesorestest;

import android.app.Application;

import com.bugsnag.android.Bugsnag;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bugsnag.start(this);
//        Bugsnag.notify(new RuntimeException("Test error"));
    }
}
