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

package com.xujiaji.learnmvvm.base;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xujiaji.learnmvvm.service.repository.Net;
import com.xujiaji.mvvmquick.base.MQViewModel;

import javax.inject.Inject;

import dagger.Lazy;

public class BaseViewModel extends MQViewModel {

    /**
     * 更新时的第一页页码
     */
    public static final int UPDATE_INDEX = 0;
    /**
     * 加载初始偏移度
     */
    public static final int INIT_LOAD_OFFSET = 0;

    @Inject
    protected Lazy<Net> net;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public int timeout() {
        return Net.TIME_OUT_READ;
    }

    @Override
    public int initLoadOffset() {
        return INIT_LOAD_OFFSET;
    }
}
