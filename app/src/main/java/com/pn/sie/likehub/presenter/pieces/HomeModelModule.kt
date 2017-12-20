package com.pn.sie.likehub.presenter.pieces

import com.pn.sie.likehub.api.GithubService
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
 * Description: 这个类没被使用, 因为它只提供了Model的依赖件(View未对外提供给P层使用)
 * </p>
 */
@Module
class HomeModelModule {
    @Provides
    fun provideHomeMyReposModel(githubService: GithubService): IHomeMyReposModel {
        return HomeMyReposModel(githubService)
    }
}