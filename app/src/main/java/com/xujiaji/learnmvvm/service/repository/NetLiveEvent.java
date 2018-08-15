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

package com.xujiaji.learnmvvm.service.repository;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class NetLiveEvent<T> extends MutableLiveData<MutableLiveData<T>> {


    //抽取观察网络请求数据逻辑

    /**
     * 观察数据
     */
    public void observeData(@NonNull final LifecycleOwner owner, @NonNull final DataCallback<T> callback) {
        observeData(owner, false, callback);
    }

    /**
     * 观察数据
     *
     * @param dataNullable 数据结果是否可能为null
     */
    public void observeData(@NonNull final LifecycleOwner owner, boolean dataNullable, @NonNull final DataCallback<T> callback) {
        observe(owner, new Observer<MutableLiveData<T>>() {
            @Override
            public void onChanged(@Nullable MutableLiveData<T> resultMutableLiveData) {
                if (resultMutableLiveData == null) {
                    callback.finished();
                    callback.fail(-100, null);
                    return;
                }
                resultMutableLiveData.observe(owner, new Observer<T>() {
                    @Override
                    public void onChanged(@Nullable T tResult) {
                        callback.finished();
                        if (tResult == null) {
                            callback.fail(-100, null);
                        } else {
                            callback.success(tResult);
                        }
                    }
                });
            }
        });

    }

}
