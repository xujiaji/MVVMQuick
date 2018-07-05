package com.xujiaji.learnmvvm.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.xujiaji.learnmvvm.service.repository.Api;
import com.xujiaji.learnmvvm.module.projectlist.ProjectListViewModel;
import com.xujiaji.learnmvvm.module.projectdetail.ProjectViewModel;
import com.xujiaji.mvvmquick.viewmodel.ProjectViewModelFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: xujiaji
 * created on: 2018/6/12 11:51
 * description:
 */
@Module(subcomponents = ViewModelSubComponent.class)
public abstract class AppModule
{

    @Singleton
    @Provides
    static Api provideApi()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .baseUrl(Api.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
    }

    @Singleton
    @Provides
    static Map<Class<?>, Callable<Lazy<? extends ViewModel>>> providesViewModel(ViewModelSubComponent.Builder viewModelSubComponent)
    {
        ViewModelSubComponent vmsc = viewModelSubComponent.build();
        Map<Class<?>, Callable<Lazy<? extends ViewModel>>> creators = new HashMap<>();
        creators.put(ProjectViewModel.class, () -> vmsc.projectViewModel());
        creators.put(ProjectListViewModel.class, () -> vmsc.projectListViewModel());
        return creators;
    }

    @Singleton
    @Binds
    abstract ViewModelProvider.Factory provideViewModelFactory(ProjectViewModelFactory factory);
}
