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

package com.xujiaji.learnmvvm.module.projectdetail;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.xujiaji.learnmvvm.base.BaseViewModel;
import com.xujiaji.learnmvvm.service.model.Project;
import com.xujiaji.learnmvvm.service.repository.NetLiveEvent;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/6/11 22:12
 * description:
 */
public class ProjectViewModel extends BaseViewModel {
    private static final NetLiveEvent ABSENT = new NetLiveEvent();

    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }


    private final NetLiveEvent<Project> projectObservable = new NetLiveEvent<>();

    public ObservableField<Project> project = new ObservableField<>();

    @Inject
    public ProjectViewModel(@NonNull Application application) {
        super(application);
    }

    public NetLiveEvent<Project> getObservableProject(String projectId) {
        projectObservable.setValue(net.get().getProjectDetails("xujiaji", projectId));
        return projectObservable;
    }

    public void setProject(Project project) {
        this.project.set(project);
    }

}
