package com.xujiaji.mvvmquick.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xujiaji.mvvmquick.R;

import java.util.List;

/**
 * author: xujiaji
 * created on: 2018/7/4 22:23
 * description:
 */
public abstract class MQQuickAdapter<T, B extends ViewDataBinding> extends BaseQuickAdapter<T, MQViewHolder<B>>
{

    public MQQuickAdapter(int layoutResId)
    {
        super(layoutResId);
    }

    public MQQuickAdapter(@Nullable List<T> data)
    {
        super(data);
    }

    public MQQuickAdapter(int layoutResId, @Nullable List<T> data)
    {
        super(layoutResId, data);
    }


    @Override
    protected View getItemView(int layoutResId, ViewGroup parent)
    {
        if (layoutResId != mLayoutResId) return super.getItemView(layoutResId, parent);

        B binding = DataBindingUtil.inflate(
                mLayoutInflater,
                layoutResId,
                parent,
                false);
        onBinding(binding);
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    /**
     * 已初始化Binding
     */
    protected abstract void onBinding(B binding);
}
