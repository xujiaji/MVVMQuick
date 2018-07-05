package com.xujiaji.learnmvvm.module.projectlist;

import com.xujiaji.learnmvvm.R;
import com.xujiaji.learnmvvm.databinding.ProjectListItemBinding;
import com.xujiaji.learnmvvm.service.model.Project;
import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.mvvmquick.base.MQViewHolder;
import com.xujiaji.mvvmquick.callback.GeneralClickCallback;
import com.xujiaji.mvvmquick.di.ActivityScoped;
import com.xujiaji.mvvmquick.di.FragmentScoped;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/6/12 10:06
 * description:
 */
public class ProjectAdapter extends MQQuickAdapter<Project, ProjectListItemBinding>
{

    private final ProjectListViewModel mViewModel;

    @Inject
    public ProjectAdapter(ProjectListViewModel viewModel)
    {
        super(R.layout.project_list_item, new ArrayList<>());
        this.mViewModel = viewModel;
    }


    @Override
    protected void onBinding(ProjectListItemBinding binding)
    {
        binding.setCallback((GeneralClickCallback<Project>) project -> mViewModel.getClickProjectEvent().setValue(project));
    }

    @Override
    protected void convert(MQViewHolder<ProjectListItemBinding> helper, Project item)
    {
        helper.binding.setProject(item);
        helper.binding.executePendingBindings();
    }
}
