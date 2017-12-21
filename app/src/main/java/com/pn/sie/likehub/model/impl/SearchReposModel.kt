package com.pn.sie.likehub.model.impl

import android.arch.lifecycle.LiveData
import com.pn.sie.likehub.api.ApiResponse
import com.pn.sie.likehub.api.GithubService
import com.pn.sie.likehub.model.ISearchReposModel
import com.pn.sie.likehub.model.entity.RepoSearchResponse

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
class SearchReposModel constructor(var githubService: GithubService) : ISearchReposModel {
    override fun getSearchRepos(query: String): LiveData<ApiResponse<RepoSearchResponse>> {
        return githubService.searchRepos(query)
    }
}