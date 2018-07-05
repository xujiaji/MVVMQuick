package com.xujiaji.mvvmquick.util;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * author: xujiaji
 * created on: 2018/6/13 15:32
 * description: Fragment 工具类
 */
public class FragmentUtils
{

    /**
     * 创建Fragment的实例
     *
     * @param keys   设置参数时的key
     * @param values 对应key的值
     * @return 创建好的Fragment实例
     */
    public static <T extends Fragment> T setArgs(T fragment, String[] keys, String ... values)
    {

        if (keys != null && values != null && keys.length != 0)
        {
            if (keys.length != values.length)
                throw new RuntimeException("keys size must be equal values size");

            Bundle args = new Bundle();

            for (int i = 0; i < keys.length; i++)
            {
                args.putString(keys[i], values[i]);
            }

            fragment.setArguments(args);
        }

        return fragment;

    }

}
