package com.pn.sie.likehub.view.fragment

import com.pn.sie.likehub.api.GithubService
import com.pn.sie.likehub.contract.IHomeMyRepos
import com.pn.sie.likehub.model.IHomeMyReposModel
import com.pn.sie.likehub.model.impl.HomeMyReposModel
import dagger.Module
import dagger.Provides

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: 提供View的Module模块, 提供View层, 并且将其注入(Binds)给Presenter
 * </p>
 */
@Module
class HomeModule {

    @Provides
    fun modelProvider(githubService: GithubService): IHomeMyReposModel {
        return HomeMyReposModel(githubService)
    }

    @Provides
    fun viewProvider(fragment: MainFragment): IHomeMyRepos.IView {
        return fragment
    }
}