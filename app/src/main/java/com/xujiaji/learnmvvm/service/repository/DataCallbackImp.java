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


import android.widget.Toast;

import com.xujiaji.learnmvvm.base.App;

/**
 * author: xujiaji
 * created on: 2018/8/12 0:33
 * description:
 */
public abstract class DataCallbackImp<T> implements DataCallback<T> {

    private boolean isToastErr = true;

    public DataCallbackImp() {
    }

    public DataCallbackImp(boolean isToastErr) {
        this.isToastErr = isToastErr;
    }

    @Override
    public void finished() {

    }

    @Override
    public void fail(int code, String msg) {
        if (isToastErr && msg != null) {
            Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
