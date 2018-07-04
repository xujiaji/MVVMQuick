package com.xujiaji.mvvmquick.binding.adapter;

import android.databinding.BindingAdapter;
import android.view.View;

public class VisibleGoneBindingAdapter
{
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}