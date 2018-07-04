package com.xujiaji.learnmvvm.view.ui;

import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.xujiaji.learnmvvm.R;
import com.xujiaji.learnmvvm.service.model.Project;
import com.xujiaji.mvvmquick.base.MQActivity;
import com.xujiaji.mvvmquick.util.ActivityUtils;
import com.xujiaji.mvvmquick.util.FragmentUtils;

import static com.xujiaji.learnmvvm.view.ui.ProjectFragment.KEY_PROJECT_ID;


public class MainActivity extends MQActivity<ViewDataBinding, AndroidViewModel>
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
        {
            ActivityUtils.addFragmentInActivity(
                    getSupportFragmentManager(),
                    new ProjectListFragment(),
                    R.id.fragment_container,
                    ProjectListFragment.class.getSimpleName());

        }
    }


    public void show(Project project)
    {
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(),
                FragmentUtils.create(ProjectFragment.class, new String[]{KEY_PROJECT_ID}, new String[]{project.name}),
                R.id.fragment_container,
                null,
                "project");
    }

}
