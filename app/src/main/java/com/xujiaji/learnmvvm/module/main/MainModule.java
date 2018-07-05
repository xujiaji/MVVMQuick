package com.xujiaji.learnmvvm.module.main;

import com.xujiaji.learnmvvm.module.projectdetail.ProjectFragment;
import com.xujiaji.learnmvvm.module.projectlist.ProjectListFragment;
import com.xujiaji.mvvmquick.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * author: xujiaji
 * created on: 2018/6/12 13:44
 * description:
 */
@Module
public abstract class MainModule
{
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ProjectFragment contributeProjectFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ProjectListFragment contributeProjectListFragment();


}
