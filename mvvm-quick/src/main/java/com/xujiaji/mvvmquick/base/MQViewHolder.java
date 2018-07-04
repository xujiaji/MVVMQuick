package com.xujiaji.mvvmquick.base;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xujiaji.mvvmquick.R;

public class MQViewHolder<B extends ViewDataBinding> extends BaseViewHolder
{
    public final B binding;

    public MQViewHolder(View view)
    {
        super(view);
        this.binding = (B) view.getTag(R.id.BaseQuickAdapter_databinding_support);
    }

}