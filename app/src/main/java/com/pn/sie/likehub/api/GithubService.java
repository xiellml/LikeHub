/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pn.sie.likehub.api;

import android.arch.lifecycle.LiveData;

import com.pn.sie.likehub.model.entity.Contributor;
import com.pn.sie.likehub.model.entity.Repo;
import com.pn.sie.likehub.model.entity.RepoSearchResponse;
import com.pn.sie.likehub.model.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * REST API access points
 */
public interface GithubService {

    //用户信息: https://api.github.com/users/{username/loginId}/
    @GET("users/{login}")
    LiveData<ApiResponse<User>> getUser(@Path("login") String login);

    //用户所有仓库: 某人的所有repos, 每个repo信息封装为一个对象
    @GET("users/{login}/repos")
    LiveData<ApiResponse<List<Repo>>> getRepos(@Path("login") String login);

    //仓库详情
    @GET("repos/{owner}/{name}")
    LiveData<ApiResponse<Repo>> getRepo(@Path("owner") String owner, @Path("name") String name);

    //仓库贡献者
    @GET("repos/{owner}/{name}/contributors")
    LiveData<ApiResponse<List<Contributor>>> getContributors(@Path("owner") String owner, @Path("name") String name);

    //关键字搜索仓库
    @GET("search/repositories")
    LiveData<ApiResponse<RepoSearchResponse>> searchRepos(@Query("q") String query);

    //关键字分页搜索仓库
    @GET("search/repositories")
    Call<RepoSearchResponse> searchRepos(@Query("q") String query, @Query("page") int page);
}