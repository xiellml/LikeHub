package com.pn.sie.likehub.model.impl

import com.pn.sie.likehub.api.GithubService
import com.pn.sie.likehub.model.IHomeMyReposModel
import com.pn.sie.likehub.model.callbak.LoadReposCallback
import javax.inject.Inject

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO 或许不再需要LoadReposCallback?, 使用LiveData会动态绑定到视图, 视图控件只要监听被观察者就OK
 * </p>
 */
class HomeMyReposModel @Inject constructor(var githubService: GithubService) : IHomeMyReposModel {

    override fun getServerMyRepos(isRefresh: Boolean, callBack: LoadReposCallback) {
        githubService.getRepos("sieml")//todo 可以设计为可更改用户; 若需要本地化则需要把getRepos()再包裹一层如: new NetworkBoundResource<List<Repo>, List<Repo>>
    }
}