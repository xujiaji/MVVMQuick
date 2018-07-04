package com.xujiaji.mvvmquick.callback;

import android.arch.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: xujiaji
 * created on: 2018/6/12 16:42
 * description: 网络请求回调统一处理
 */
public class NetCallback<T> implements Callback<T>
{
    private final MutableLiveData<T> mutableLiveData;
    public NetCallback(MutableLiveData<T> mutableLiveData)
    {
        this.mutableLiveData = mutableLiveData;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response)
    {
        mutableLiveData.setValue(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t)
    {
        mutableLiveData.setValue(null);
    }
}
