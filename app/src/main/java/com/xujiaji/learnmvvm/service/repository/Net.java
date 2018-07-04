package com.xujiaji.learnmvvm.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.xujiaji.mvvmquick.callback.NetCallback;
import com.xujiaji.learnmvvm.service.model.Project;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

/**
 * author: xujiaji
 * created on: 2018/6/11 21:50
 * description:
 */
@Singleton
public class Net
{
    private Api mApi;

    @Inject
    public Net(Api api)
    {
        this.mApi = api;
    }

    /**
     * 统一数据处理
     */
    private <T> LiveData<T> handle(Call<T> call)
    {
        final MutableLiveData<T> data = new MutableLiveData<>();
        call.enqueue(new NetCallback<>(data));
        return data;
    }

    /**
     * 获取项目列表
     */
    public LiveData<List<Project>> getProjectList(String userId)
    {
        return handle(mApi.getProjectList(userId));
    }

    /**
     * 获取项目详情信息
     */
    public LiveData<Project> getProjectDetails(String userId, String projectName)
    {
        return handle(mApi.getProjectDetails(userId, projectName));
    }
}
