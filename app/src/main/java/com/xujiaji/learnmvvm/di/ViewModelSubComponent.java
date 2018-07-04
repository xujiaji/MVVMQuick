package com.xujiaji.learnmvvm.di;

import com.xujiaji.learnmvvm.viewmodel.ProjectListViewModel;
import com.xujiaji.learnmvvm.viewmodel.ProjectViewModel;

import dagger.Subcomponent;

/**
 * author: xujiaji
 * created on: 2018/6/12 13:20
 * description:
 */
@Subcomponent
public interface ViewModelSubComponent
{
    @Subcomponent.Builder
    interface Builder
    {
        ViewModelSubComponent build();
    }

    ProjectListViewModel projectListViewModel();
    ProjectViewModel projectViewModel();
}
