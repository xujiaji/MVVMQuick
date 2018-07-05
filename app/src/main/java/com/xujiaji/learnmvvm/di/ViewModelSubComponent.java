package com.xujiaji.learnmvvm.di;

import com.xujiaji.learnmvvm.module.projectlist.ProjectListViewModel;
import com.xujiaji.learnmvvm.module.projectdetail.ProjectViewModel;

import dagger.Lazy;
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

    Lazy<ProjectListViewModel> projectListViewModel();
    Lazy<ProjectViewModel> projectViewModel();
}
