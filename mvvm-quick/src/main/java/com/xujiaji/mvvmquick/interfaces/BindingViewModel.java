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

package com.xujiaji.mvvmquick.interfaces;

import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * author: xujiaji
 * created on: 2018/7/4 23:19
 * description:
 */
public interface BindingViewModel<B extends ViewDataBinding, VM extends AndroidViewModel> {
    /**
     * 实例化Binding后调用该方法
     */
    void onBinding(@NonNull B binding);


    /**
     * 实例化ViewModel后调用该方法
     */
    void onObserveViewModel(@NonNull VM viewModel);


    /**
     * 在 super {@link android.app.Activity#onCreate(Bundle)}之前被调用
     */
    void onBeforeCreate();

    /**
     * 在 super {@link android.app.Activity#onCreate(Bundle)}之前被调用，并且有Bundle
     * @param savedInstanceState 该参数不可能为null
     */
    void onBundleHandle(@NonNull Bundle savedInstanceState);

    /**
     * 写一些初始化的操作
     * 在 {@link #onObserveViewModel(AndroidViewModel)}  之后被调用
     */
    void onInit();
    /**
     * 这里面写监听事件
     * 在 {@link #onInit()}  之后被调用
     */
    void onListener();

}
