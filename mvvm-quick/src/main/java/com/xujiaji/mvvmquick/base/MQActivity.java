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

package com.xujiaji.mvvmquick.base;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.xujiaji.mvvmquick.interfaces.MQView;
import com.xujiaji.mvvmquick.util.ClassUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * author: xujiaji
 * created on: 2018/7/2 14:23
 * description:
 */
public abstract class MQActivity<B extends ViewDataBinding, VM extends AndroidViewModel> extends DaggerAppCompatActivity implements MQView<B, VM> {

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    protected B binding;

    protected VM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onBeforeCreate();
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            // 页面已被启用，但因内存不足页面被系统销毁过
            onBundleHandle(savedInstanceState);
        } else {
            // 第一次进入页面获取上个页面传递过来的数据
            Intent intent = getIntent();
            if (intent != null) {
                onIntentHandle(intent);
            }
        }
        initBinding();
        initViewModel();

        onInit();
        onListener();
    }

    /**
     * 初始化ViewModel
     */
    private void initViewModel() {
        Class<VM> viewModelClass = ClassUtils.getViewModel(this);
        if (viewModelClass == null) return;
        final VM viewModel = ViewModelProviders.of(this, mViewModelFactory).get(viewModelClass);
        this.viewModel = viewModel;
        onObserveViewModel(viewModel);
    }

    /**
     * 初始化Binding
     */
    private void initBinding() {
        binding = ClassUtils.getBinding(this, getLayoutInflater(), ((ViewGroup) findViewById(android.R.id.content)));
        if (binding != null) {
            setContentView(binding.getRoot());
            onBinding(binding);
        }
    }

    // 非standard的启动模式，第二次之后不会进入onCreate周期，转而是onNewIntent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent != null) {
            onIntentHandle(intent);
        }


        onListener();
    }

    @Override
    public void onObserveViewModel(@NonNull VM viewModel) {

    }

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public void onBundleHandle(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void onInit() {

    }

    @Override
    public void onListener() {

    }

    @Override
    public void onIntentHandle(@NonNull Intent intent) {

    }
}
