[![Version](https://img.shields.io/badge/version-0.0.3-green.svg)]()
# MVVMQuick
旨在快速以MVVM开始开发项目，其内集成了dagger2、okhttp3、retrofit、BaseRecyclerViewAdapterHelper、lifecycle等常用的框架和一些工具。

将大部分逻辑抽象，希望可以通过几步简单的配置，即可省去重复逻辑的代码。

# Note
目前还在测试开发中，有兴趣的朋友可以体验体验给给建议哦！如要运用到项目中请自行下载项目导入`mvvm-quik`模块，也可直接以该项目开始开发。

该项目参考：

1. [学习MVVM的实际运用，并且结合Dagger](https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-2-di-1a6b1f96d84b)
2. [todo-mvp-dagger](https://github.com/googlesamples/android-architecture/tree/todo-mvp-dagger)
3. [todo-mvvm-live](https://github.com/googlesamples/android-architecture/tree/todo-mvvm-live)
4. [todo-mvvm-databinding](https://github.com/googlesamples/android-architecture/tree/todo-mvvm-databinding)
5. [mvvm-sample-app](https://github.com/hazems/mvvm-sample-app)
6. [LearnMVVM](https://github.com/xujiaji/learn-android/tree/learn-mvvm)

## dependencies

```
implementation 'com.github.xujiaji:mvvm-quick:0.0.3'
```

# Use
详细使用案例请参考：https://github.com/xujiaji/WanAndroid

> 1.定义ViewModel，继承`MQViewModel`

``` java
@Singleton
public class ProjectListViewModel extends MQViewModel
{
    ...
}
```
> 2.布局文件`fragment_project_list`以DataBinding的写法

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="projectListViewModel"
            type="com.xujiaji.learnmvvm.module.projectlist.ProjectListViewModel"/>
    </data>
    ...
</layout>
```
> 3.ui中的写法，将DataBinding和ViewModel作为泛型配置，MQFragment会自动为您自动实例化

```java
@ActivityScoped
public class ProjectListFragment extends MQFragment<FragmentProjectListBinding, ProjectListViewModel>
{

    @Inject
    Lazy<ProjectAdapter> mAdapter;

    @Inject
    public ProjectListFragment() {}

    @Override
    public void onBinding(FragmentProjectListBinding binding)
    {
        ... 当Binding初始化后会调用
    }

    @Override
    public void onObserveViewModel(ProjectListViewModel viewModel)
    {
        ... 当ViewModel初始化后会调用（在Binding之后初始化）
    }
}
```
> 4.在[ViewModelSubComponent](app/src/main/java/com/xujiaji/learnmvvm/di/ViewModelSubComponent.java)中添加配置，提供ViewModel实例（实例提供通过Dagger实现）

```java
@Subcomponent
public interface ViewModelSubComponent
{
    ...
    Lazy<ProjectListViewModel> projectListViewModel(); //add
}
```
> 5.在[AppModule](app/src/main/java/com/xujiaji/learnmvvm/di/AppModule.java)的`providesViewModel`方法中添加配置

```java
    @Singleton
    @Provides
    static Map<Class<?>, Callable<Lazy<? extends ViewModel>>> providesViewModel(ViewModelSubComponent.Builder viewModelSubComponent)
    {
        ...
        creators.put(ProjectListViewModel.class, vmsc::projectListViewModel);//add
        return creators;
    }
```

# License
```
   Copyright 2018 XuJiaji

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```