package com.xujiaji.mvvmquick.base;

import android.content.Context;

import dagger.android.support.DaggerApplication;

/**
 * author: xujiaji
 * created on: 2018/7/2 14:18
 * description:
 */
public abstract class MQApp extends DaggerApplication
{

    private static Context mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this.getApplicationContext();
//        AppInjector.init(this);
    }

    public static Context getInstance()
    {
        return mInstance;
    }

}
