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

package com.xujiaji.learnmvvm.module.start.guide;

import com.xujiaji.learnmvvm.databinding.FragmentGuideBinding;
import com.xujiaji.mvvmquick.base.MQFragment;
import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.di.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class GuideFragment extends MQFragment<FragmentGuideBinding, MQViewModel>
{
    @Inject
    public GuideFragment() {}
}
