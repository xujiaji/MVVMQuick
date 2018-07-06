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

package com.xujiaji.learnmvvm.module.start.welcome;

import android.arch.lifecycle.AndroidViewModel;
import android.view.View;

import com.bumptech.glide.Glide;
import com.qfxl.view.RoundProgressBar;
import com.xujiaji.learnmvvm.databinding.FragmentWelcomeBinding;
import com.xujiaji.learnmvvm.module.main.MainActivity;
import com.xujiaji.mvvmquick.base.MQFragment;
import com.xujiaji.mvvmquick.di.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class WelcomeFragment extends MQFragment<FragmentWelcomeBinding, AndroidViewModel>
{

    private boolean isOver;

    @Inject
    public WelcomeFragment() {}

    @Override
    public void onBinding(FragmentWelcomeBinding binding)
    {
        super.onBinding(binding);
        Glide.with(this)
                .load("https://raw.githubusercontent.com/xujiaji/xujiaji.github.io/pictures/mvvmquick/welcome2.png")
                .into(binding.adPic);

        binding.roundProgressBar.setProgressChangeListener(new RoundProgressBar.ProgressChangeListener()
        {
            @Override
            public void onFinish()
            {
                goMainActivity();
            }

            @Override
            public void onProgressChanged(int progress)
            {

            }
        });

        binding.roundProgressBar.setOnClickListener(view -> goMainActivity());

    }

    private void goMainActivity()
    {
        if (getActivity() == null || isOver) return;
        isOver = true;
        MainActivity.launch(getActivity());
        getActivity().finish();
    }
}
