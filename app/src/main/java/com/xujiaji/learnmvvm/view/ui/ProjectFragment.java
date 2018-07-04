package com.xujiaji.learnmvvm.view.ui;

import android.os.Bundle;
import android.util.Log;

import com.xujiaji.mvvmquick.base.MQFragment;
import com.xujiaji.learnmvvm.databinding.FragmentProjectDetailsBinding;
import com.xujiaji.learnmvvm.viewmodel.ProjectViewModel;


/**
 * author: xujiaji
 * created on: 2018/6/12 10:43
 * description:
 */
public class ProjectFragment extends MQFragment<FragmentProjectDetailsBinding, ProjectViewModel>
{
    public static final String KEY_PROJECT_ID = "project_id";

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
