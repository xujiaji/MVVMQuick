package com.xujiaji.learnmvvm.module.projectdetail;

import android.os.Bundle;

import com.xujiaji.mvvmquick.base.MQFragment;
import com.xujiaji.learnmvvm.databinding.FragmentProjectDetailsBinding;
import com.xujiaji.mvvmquick.di.ActivityScoped;

import javax.inject.Inject;


/**
 * author: xujiaji
 * created on: 2018/6/12 10:43
 * description:
 */
@ActivityScoped
public class ProjectFragment extends MQFragment<FragmentProjectDetailsBinding, ProjectViewModel>
{
    public static final String KEY_PROJECT_ID = "project_id";

    @Inject
    public ProjectFragment() {}

    @Override
    public void onBinding(FragmentProjectDetailsBinding binding)
    {
        binding.setIsLoading(true);
    }

    @Override
    public void onObserveViewModel(ProjectViewModel viewModel)
    {
        binding.setProjectViewModel(viewModel);
        Bundle bundle = getArguments();
        if (bundle != null)
        {
            viewModel.setProjectID(bundle.getString(KEY_PROJECT_ID));
        }

        viewModel.getObservableProject().observe(this, project ->
        {
            if (project != null) {
                binding.setIsLoading(false);
                viewModel.setProject(project);
            }
        });

    }

}
