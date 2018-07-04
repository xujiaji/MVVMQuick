package com.xujiaji.learnmvvm.view.adapter;

import com.xujiaji.learnmvvm.R;
import com.xujiaji.learnmvvm.databinding.ProjectListItemBinding;
import com.xujiaji.learnmvvm.service.model.Project;
import com.xujiaji.learnmvvm.viewmodel.ProjectListViewModel;
import com.xujiaji.mvvmquick.base.MQQuickAdapter;
import com.xujiaji.mvvmquick.base.MQViewHolder;
import com.xujiaji.mvvmquick.callback.GeneralClickCallback;

import java.util.ArrayList;

/**
 * author: xujiaji
 * created on: 2018/6/12 10:06
 * description:
 */
public class ProjectAdapter extends MQQuickAdapter<Project, ProjectListItemBinding>
{

    private final ProjectListViewModel mViewModel;

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
