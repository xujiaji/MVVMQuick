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

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.xujiaji.learnmvvm.databinding.FragmentProjectDetailsBinding;
import com.xujiaji.learnmvvm.service.model.Project;
import com.xujiaji.learnmvvm.service.repository.DataCallbackImp;
import com.xujiaji.mvvmquick.base.MQFragment;
import com.xujiaji.mvvmquick.di.ActivityScoped;

import javax.inject.Inject;


/**
 * author: xujiaji
 * created on: 2018/6/12 10:43
 * description:
 */
@ActivityScoped
public class ProjectFragment extends MQFragment<FragmentProjectDetailsBinding, ProjectViewModel> {
    public static final String KEY_PROJECT_ID = "project_id";

    private String projectId;

    @Inject
    public ProjectFragment() {
    }

    @Override
    public void onBinding(@NonNull FragmentProjectDetailsBinding binding) {
        binding.setIsLoading(true);
    }

    @Override
    public void onArgumentsHandle(@NonNull Bundle bundle) {
        projectId = bundle.getString(KEY_PROJECT_ID);
    }

    @Override
    public void onObserveViewModel(@NonNull ProjectViewModel viewModel) {
        binding.setProjectViewModel(viewModel);
    }

    @Override
    public void onLazyLoad() {
        viewModel.getObservableProject(projectId).observeData(this, new DataCallbackImp<Project>() {
            @Override
            public void success(Project bean) {
                binding.setIsLoading(false);
                viewModel.setProject(bean);
            }
        });
    }

    @Override
    public boolean isInViewPager() {
        return false;
    }
}
