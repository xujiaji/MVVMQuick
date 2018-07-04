package com.xujiaji.mvvmquick.util;

import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author: xujiaji
 * created on: 2018/6/13 14:24
 * description:
 */
public class ClassUtils
{
    private static <T> Class<T> getGenericClass(Class<?> klass, Class<?> filterClass)
    {
        Type type = klass.getGenericSuperclass();
        if (type == null || !(type instanceof ParameterizedType)) return null;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        for (Type t : types)
        {
            Class<T> tClass = (Class<T>) t;
            if (filterClass.isAssignableFrom(tClass))
            {
                return tClass;
            }
        }
        return null;
    }


    /**
     * 获取泛型ViewModel的class对象
     */
    public static <T> Class<T> getViewModel(Object obj)
    {
        Class<?> currentClass = obj.getClass();
        Class<T> tClass = getGenericClass(currentClass, AndroidViewModel.class);
        if (tClass == null || tClass == AndroidViewModel.class)
        {
            return null;
        }
        return tClass;
    }

    /**
     * 获取泛型Binding的class对象
     */
    public static <T> T getBinding(@NonNull Object obj, @NonNull LayoutInflater inflater, @Nullable ViewGroup container)
    {
        Class<?> currentClass = obj.getClass();
        Class<T> tClass = getGenericClass(currentClass, ViewDataBinding.class);
        if (tClass == null || tClass == ViewDataBinding.class)
        {
            return null;
        }
        try
        {
            Method method = tClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, Boolean.TYPE);
            Object returnValue = method.invoke(null, inflater, container, false);
            return (T) returnValue;
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
    }
}
