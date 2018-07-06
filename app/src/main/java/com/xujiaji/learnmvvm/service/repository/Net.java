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

package com.xujiaji.learnmvvm.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.xujiaji.mvvmquick.callback.NetCallback;
import com.xujiaji.learnmvvm.service.model.Project;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

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
    private Map<String, WeakReference<MutableLiveData<?>>> mLiveDataMap = new HashMap<>();

    @Inject
    public Net(Api api)
    {
        this.mApi = api;
    }

    /**
     * 统一数据处理
     */
    private <T> LiveData<T> handle(String key, Call<T> call)
    {
        final MutableLiveData<T> data;
        if (mLiveDataMap.containsKey(key) && mLiveDataMap.get(key).get() != null)
        {
            data = (MutableLiveData<T>) mLiveDataMap.get(key).get();
        } else
        {
            data = new MutableLiveData<>();
            mLiveDataMap.put(key, new WeakReference<>(data));
        }
        call.enqueue(new NetCallback<>(data));
        return data;
    }

    /**
     * 获取项目列表
     */
    public LiveData<List<Project>> getProjectList(String userId)
    {
        return handle("getProjectList", mApi.getProjectList(userId));
    }

    /**
     * 获取项目详情信息
     */
    public LiveData<Project> getProjectDetails(String userId, String projectName)
    {
        return handle("getProjectDetails", mApi.getProjectDetails(userId, projectName));
    }
}
