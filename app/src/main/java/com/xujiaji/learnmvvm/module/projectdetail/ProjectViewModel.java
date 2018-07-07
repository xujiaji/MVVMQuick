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
import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.xujiaji.learnmvvm.service.model.Project;
import com.xujiaji.learnmvvm.service.repository.Net;
import com.xujiaji.mvvmquick.base.MQViewModel;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/6/11 22:12
 * description:
 */
public class ProjectViewModel extends MQViewModel
{
    private static final String TAG = ProjectViewModel.class.getName();
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    private final LiveData<Project> projectObservable;
    private final MutableLiveData<String> projectID;
    public ObservableField<Project> project = new ObservableField<>();

    @Inject
    public ProjectViewModel(@NonNull Net net, @NonNull Application application)
    {
        super(application);
        this.projectID = new MutableLiveData<>();
        projectObservable = Transformations.switchMap(projectID, (Function<String, LiveData<Project>>) input ->
        {
            if (input.isEmpty())
            {
                Log.i(TAG, "ProjectViewModel projectID is absent!!!");
                return ABSENT;
            }
            Log.i(TAG,"ProjectViewModel projectID is " + projectID.getValue());
            return net.getProjectDetails("xujiaji", projectID.getValue());
        });
    }

    public LiveData<Project> getObservableProject()
    {
        return projectObservable;
    }

    public void setProject(Project project)
    {
        this.project.set(project);
    }

    public void setProjectID(String projectID)
    {
        this.projectID.setValue(projectID);
    }
}
