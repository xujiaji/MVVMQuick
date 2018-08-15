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

import android.arch.lifecycle.MutableLiveData;

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
public class Net {
    /**
     * 读取超时
     */
    public static final int TIME_OUT_READ = 20;

    /**
     * 连接超时
     */
    public static final int TIME_OUT_CONNECT = 5;

    private Api mApi;

    @Inject
    public Net(Api api) {
        this.mApi = api;
    }

    /**
     * 统一数据处理
     */
    private <T> MutableLiveData<T> handle(Call<T> call) {
        final MutableLiveData<T> data = new MutableLiveData<>();
        call.enqueue(new NetCallback<>(data));
        return data;
    }

    /**
     * 获取项目列表
     */
    public MutableLiveData<List<Project>> getProjectList(String userId) {
        return handle(mApi.getProjectList(userId));
    }

    /**
     * 获取项目详情信息
     */
    public MutableLiveData<Project> getProjectDetails(String userId, String projectName) {
        return handle(mApi.getProjectDetails(userId, projectName));
    }
}
