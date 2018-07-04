package com.xujiaji.mvvmquick.interfaces;

import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ViewDataBinding;

/**
 * author: xujiaji
 * created on: 2018/7/4 23:19
 * description:
 */
public interface BindingViewModel<B extends ViewDataBinding, VM extends AndroidViewModel>
{
    /**
     * 实例化Binding后调用该方法
     */
    void onBinding(B binding);


    /**
     * 实例化ViewModel后调用该方法
     */
    void onObserveViewModel(VM viewModel);
}
