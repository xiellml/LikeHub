package com.pn.sie.likehub.model

import android.arch.lifecycle.LiveData
import com.pn.sie.likehub.api.ApiResponse
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
interface ISearchReposModel {
    fun getSearchRepos(query: String): LiveData<ApiResponse<RepoSearchResponse>>
}