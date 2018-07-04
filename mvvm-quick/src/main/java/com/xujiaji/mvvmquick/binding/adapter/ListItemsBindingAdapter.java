package com.xujiaji.mvvmquick.binding.adapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * author: xujiaji
 * created on: 2018/6/13 16:21
 * description:
 */
public class ListItemsBindingAdapter
{
    @BindingAdapter("items")
    public static <T, V extends BaseViewHolder> void setItems(RecyclerView recyclerView, List<T> items)
    {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null || ! (adapter instanceof BaseQuickAdapter)) return;
        BaseQuickAdapter<T, V> ad = (BaseQuickAdapter<T, V>) adapter;
        ad.setNewData(items);
    }
}

