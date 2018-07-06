/*
 *    Copyright 2018 XuJiaji
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.xujiaji.learnmvvm.module.projectlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.xujiaji.learnmvvm.service.model.Project;
import com.xujiaji.learnmvvm.service.repository.Net;
import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.lifecycle.SingleLiveEvent;
import com.xujiaji.mvvmquick.viewmodel.RefreshLoadViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/6/11 22:11
 * description:
 */
@Singleton
public class ProjectListViewModel extends RefreshLoadViewModel
{
    private final LiveData<List<Project>> projectListObservable;
    private final SingleLiveEvent<Project> mClickProjectEvent = new SingleLiveEvent<>();
    private final WeakReference<Net> netWR;

    public final ObservableList<Project> items = new ObservableArrayList<>();

    @Inject
    public ProjectListViewModel(@NonNull Net net, @NonNull Application application)
    {
        super(application);
        projectListObservable = net.getProjectList("xujiaji");
        netWR = new WeakReference<>(net);
    }

    @Override
    public void toRefresh()
    {
        super.toRefresh();
        if (netWR.get() != null)
        {
            netWR.get().getProjectList("xujiaji");
        }
    }

    public LiveData<List<Project>> getProjectListObservable()
    {
        return projectListObservable;
    }

    public SingleLiveEvent<Project> getClickProjectEvent()
    {
        return mClickProjectEvent;
    }
}
