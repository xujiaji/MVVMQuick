package com.xujiaji.learnmvvm.di;

import com.xujiaji.learnmvvm.module.main.MainModule;
import com.xujiaji.learnmvvm.module.main.MainActivity;
import com.xujiaji.mvvmquick.di.ActivityScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * author: xujiaji
 * created on: 2018/6/12 13:43
 * description:
 */
@Module
public abstract class ActivityBindingModule
{
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivity();
}
