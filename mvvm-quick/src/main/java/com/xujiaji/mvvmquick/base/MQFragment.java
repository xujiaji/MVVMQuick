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

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xujiaji.mvvmquick.interfaces.MQFragView;
import com.xujiaji.mvvmquick.util.ClassUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * author: xujiaji
 * created on: 2018/6/13 13:49
 * description: Fragment基类
 */
public abstract class MQFragment<B extends ViewDataBinding, VM extends MQViewModel> extends DaggerFragment implements MQFragView<B, VM> {
    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    protected B binding;

    protected VM viewModel;


    /**
     * Fragment是否可见状态
     */
    private boolean isFragmentVisible;

    /**
     * View已经初始化完成
     */
    private boolean isPrepared;

    /**
     * 是否第一次加载
     */
    private boolean isFirstLoad = true;

    /**
     * <pre>
     * 忽略isFirstLoad的值，强制刷新数据，但仍要Visible & Prepared
     * 一般用于PagerAdapter需要刷新各个子Fragment的场景
     * 不要new 新的 PagerAdapter 而采取reset数据的方式
     * 所以要求Fragment重新走initData方法
     * 故使用 {@link #setForceLoad(boolean)}来让Fragment下次执行initData
     * </pre>
     */
    private boolean forceLoad = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        onBeforeCreate();
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            onBundleHandle(savedInstanceState);
        }

        Bundle bundle = getArguments();
        if (bundle != null && bundle.size() > 0) {
            onArgumentsHandle(bundle);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        isFirstLoad = true;
        isPrepared = true;
        if (!isInViewPager()) {
            isFragmentVisible = true;
        }

        binding = ClassUtils.getBinding(this, inflater, container);
        if (binding == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            onBinding(binding);
            return binding.getRoot();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Class<VM> viewModelClass = ClassUtils.getViewModel(this);
        if (viewModelClass == null) return;

        final VM viewModel;
        if (providerVmByActivity() && getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity(), mViewModelFactory).get(viewModelClass);
        } else {
            viewModel = ViewModelProviders.of(this, mViewModelFactory).get(viewModelClass);
        }
        this.viewModel = viewModel;
        onObserveViewModel(viewModel);

        onInit();
        onListener();
        lazyLoad();
    }


    /***
     *  如果是与ViewPager一起使用，调用的是setUserVisibleHint
     * @param isVisibleToUser 是否显示出来了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not
     * visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onInvisible();
        } else {
            onVisible();
        }
    }

    @Override
    public void onVisible() {
        isFragmentVisible = true;
        lazyLoad();
    }


    private void lazyLoad() {
        if (isPrepared() && isFragmentVisible()) {
            if (isForceLoad() || isFirstLoad()) {
                forceLoad = false;
                isFirstLoad = false;
                onLazyLoad();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            viewModel.onDestroy();
            viewModel = null;
        }
        if (binding != null) {
            binding.unbind();
        }
    }

    /**
     * 实例化ViewModel是否通过Activity
     */
    protected boolean providerVmByActivity() {
        return false;
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
    public void onArgumentsHandle(@NonNull Bundle bundle) {

    }

    @Override
    public void onInvisible() {
        isFragmentVisible = false;
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public void setForceLoad(boolean forceLoad) {
        this.forceLoad = forceLoad;
    }

    @Override
    public boolean isForceLoad() {
        return forceLoad;
    }

    @Override
    public boolean isPrepared() {
        return isPrepared;
    }

    @Override
    public boolean isFirstLoad() {
        return isFirstLoad;
    }

    @Override
    public boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    @Override
    public boolean isInViewPager() {
        return true;
    }
}
