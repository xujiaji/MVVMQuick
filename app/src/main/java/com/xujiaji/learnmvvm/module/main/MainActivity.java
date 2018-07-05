package com.xujiaji.learnmvvm.module.main;

import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.xujiaji.learnmvvm.R;
import com.xujiaji.learnmvvm.module.projectdetail.ProjectFragment;
import com.xujiaji.learnmvvm.module.projectlist.ProjectListFragment;
import com.xujiaji.learnmvvm.service.model.Project;
import com.xujiaji.mvvmquick.base.MQActivity;
import com.xujiaji.mvvmquick.util.ActivityUtils;
import com.xujiaji.mvvmquick.util.FragmentUtils;

import javax.inject.Inject;

import dagger.Lazy;

import static com.xujiaji.learnmvvm.module.projectdetail.ProjectFragment.KEY_PROJECT_ID;


public class MainActivity extends MQActivity<ViewDataBinding, AndroidViewModel>
{

    @Inject
    Lazy<ProjectListFragment> mProjectListFragment;
    @Inject
    Lazy<ProjectFragment> mProjectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
        {
            ActivityUtils.addFragmentInActivity(
                    getSupportFragmentManager(),
                    mProjectListFragment.get(),
                    R.id.fragment_container,
                    ProjectListFragment.class.getSimpleName());

        }
    }


    public void show(Project project)
    {
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(),
                FragmentUtils.setArgs(mProjectFragment.get(), new String[]{KEY_PROJECT_ID}, project.name),
                R.id.fragment_container,
                null,
                "project");
    }

}
