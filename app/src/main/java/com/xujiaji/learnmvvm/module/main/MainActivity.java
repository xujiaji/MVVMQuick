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
