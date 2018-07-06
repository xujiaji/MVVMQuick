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

package com.xujiaji.learnmvvm.module.start;

import android.os.Bundle;

import com.xujiaji.learnmvvm.R;
import com.xujiaji.learnmvvm.module.projectlist.ProjectListFragment;
import com.xujiaji.learnmvvm.module.start.guide.GuideFragment;
import com.xujiaji.learnmvvm.module.start.welcome.WelcomeFragment;
import com.xujiaji.mvvmquick.base.MQActivity;
import com.xujiaji.mvvmquick.util.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;

public class StartActivity extends MQActivity
{

    @Inject
    Lazy<WelcomeFragment> mWelcomeFragment;
    @Inject
    Lazy<GuideFragment> mGuideFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        if (savedInstanceState == null)
        {
            ActivityUtils.addFragmentInActivity(
                    getSupportFragmentManager(),
                    mWelcomeFragment.get(),
                    R.id.fragment_container,
                    ProjectListFragment.class.getSimpleName());

        }
    }
}
