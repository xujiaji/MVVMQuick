package com.xujiaji.learnmvvm.module.projectlist;

import android.arch.lifecycle.Lifecycle;

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
    }
}
