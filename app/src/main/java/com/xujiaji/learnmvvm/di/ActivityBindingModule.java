package com.xujiaji.learnmvvm.di;

import com.xujiaji.learnmvvm.view.ui.MainActivity;

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
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}
