package com.xujiaji.learnmvvm.service.repository;

import com.xujiaji.learnmvvm.service.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author: xujiaji
 * created on: 2018/6/11 21:50
 * description:
 */
public interface Api
{
    String URL = "https://api.github.com/";

    @GET("users/{user}/repos")
    Call<List<Project>> getProjectList(@Path("user") String user);

    @GET("/repos/{user}/{reponame}")
    Call<Project> getProjectDetails(@Path("user") String user, @Path("reponame") String projectName);
}
