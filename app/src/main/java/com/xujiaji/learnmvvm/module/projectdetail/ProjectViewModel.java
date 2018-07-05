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
