package com.xujiaji.mvvmquick.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xujiaji
 * created on: 2018/6/12 13:22
 * description:
 */
@Singleton
public class ProjectViewModelFactory implements ViewModelProvider.Factory
{
    private final Map<Class<?>, Callable<? extends ViewModel>> creators;

    @Inject
    public ProjectViewModelFactory(Map<Class<?>, Callable<? extends ViewModel>> creators)
    {
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null)
        {
            for (Map.Entry<Class<?>, Callable<? extends ViewModel>> entry : creators.entrySet())
            {
                if (modelClass.isAssignableFrom(entry.getKey()))
                {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null)
        {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }
        try
        {
            return (T) creator.call();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
