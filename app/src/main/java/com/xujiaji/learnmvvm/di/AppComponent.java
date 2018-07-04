package com.xujiaji.learnmvvm.di;

import android.app.Application;

import com.xujiaji.learnmvvm.base.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * author: xujiaji
 * created on: 2018/6/12 11:51
 * description:
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBindingModule.class
})
public interface AppComponent extends AndroidInjector<App>
{
    @Component.Builder
    interface Builder
    {
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }
}
