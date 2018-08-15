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

/**
 * author: xujiaji
 * created on: 2018/8/12 0:11
 * description:
 */
public interface DataCallback<T> {
    /**
     * 完成回调，不管成功还是失败
     */
    void finished();

    /**
     * 成功得到数据
     */
    void success(T bean);

    /**
     * 失败
     *
     * @param code 错误码
     * @param msg  错误消息
     */
    void fail(int code, String msg);
}
