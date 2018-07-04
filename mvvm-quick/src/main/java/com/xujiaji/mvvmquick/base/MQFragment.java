package com.xujiaji.mvvmquick.base;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xujiaji.mvvmquick.interfaces.BindingViewModel;
import com.xujiaji.mvvmquick.util.ClassUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * author: xujiaji
 * created on: 2018/6/13 13:49
 * description: Fragment基类
 */
public class MQFragment<B extends ViewDataBinding, VM extends AndroidViewModel> extends DaggerFragment implements BindingViewModel<B, VM>
{
    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    protected B binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = ClassUtils.getBinding(this, inflater, container);
        if (binding == null)
        {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else
        {
            onBinding(binding);
            return binding.getRoot();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Class<VM> viewModelClass = ClassUtils.getViewModel(this);
        if (viewModelClass == null) return;

        final VM viewModel;
        if (providerVmByActivity() && getActivity() != null)
        {
            viewModel = ViewModelProviders.of(getActivity(), mViewModelFactory).get(viewModelClass);
        } else
        {
            viewModel = ViewModelProviders.of(this, mViewModelFactory).get(viewModelClass);
        }

        onObserveViewModel(viewModel);
    }


    /**
     * 实例化ViewModel是否通过Activity
     */
    protected boolean providerVmByActivity() { return false; }

    @Override
    public void onBinding(B binding)
    {

    }

    @Override
    public void onObserveViewModel(VM viewModel)
    {

    }
}
