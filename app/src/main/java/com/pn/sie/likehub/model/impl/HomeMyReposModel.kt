package com.pn.sie.likehub.model.impl

import android.arch.lifecycle.LiveData
import com.pn.sie.likehub.api.ApiResponse
import com.pn.sie.likehub.api.GithubService
import com.pn.sie.likehub.di.ModelScoped
import com.pn.sie.likehub.model.IHomeMyReposModel
import com.pn.sie.likehub.model.entity.Repo
import dagger.Module
import dagger.Provides
import javax.inject.Inject

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description:
 * </p>
 */
class HomeMyReposModel @Inject constructor(var githubService: GithubService) : IHomeMyReposModel {
    override fun getServerMyRepos(isRefresh: Boolean): LiveData<ApiResponse<List<Repo>>> {
        //todo 可以设计为可更改用户; 若需要本地化则需要把getRepos()再包裹一层如: new NetworkBoundResource<List<Repo>, List<Repo>>
        return githubService.getRepos("sieml")
    }
}