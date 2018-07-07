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

import android.arch.lifecycle.Lifecycle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.xujiaji.learnmvvm.R;
import com.xujiaji.learnmvvm.databinding.FragmentProjectListBinding;
import com.xujiaji.learnmvvm.module.main.MainActivity;
import com.xujiaji.mvvmquick.base.MQFragment;
import com.xujiaji.mvvmquick.di.ActivityScoped;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * author: xujiaji
 * created on: 2018/6/11 22:30
 * description:
 */
@ActivityScoped
public class ProjectListFragment extends MQFragment<FragmentProjectListBinding, ProjectListViewModel>
{

    @Inject
    Lazy<ProjectAdapter> mAdapter;

    @Inject
    public ProjectListFragment() {}

    @Override
    public void onBinding(FragmentProjectListBinding binding)
    {
        binding.projectRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorPrimary);
        binding.setIsLoading(true);
    }

    @Override
    public void onObserveViewModel(ProjectListViewModel viewModel)
    {
        binding.setProjectListViewModel(viewModel);
        viewModel.getProjectListObservable().observe(this, projects ->
        {
            if (projects != null)
            {
                binding.setIsLoading(false);
                viewModel.items.clear();
                viewModel.items.addAll(projects);
            }
        });

        viewModel.getClickProjectEvent().observe(this, project ->
        {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED) && getActivity() instanceof MainActivity)
            {
                ((MainActivity) getActivity()).show(project);
            }
        });

        binding.projectList.setAdapter(mAdapter.get());
        mAdapter.get().setEmptyView(R.layout.no_item_archived, binding.projectList);
    }
}
