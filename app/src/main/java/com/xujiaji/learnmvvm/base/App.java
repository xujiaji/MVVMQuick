package com.xujiaji.learnmvvm.base;

import com.xujiaji.learnmvvm.di.DaggerAppComponent;
import com.xujiaji.mvvmquick.base.MQApp;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * author: xujiaji
 * created on: 2018/6/12 11:49
 * description:
 */
public class App extends MQApp
{

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector()
    {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }
}
