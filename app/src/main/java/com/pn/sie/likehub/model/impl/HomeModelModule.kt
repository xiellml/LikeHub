package com.pn.sie.likehub.model.impl

import com.pn.sie.likehub.api.GithubService
import com.pn.sie.likehub.model.IHomeMyReposModel
import dagger.Module
import dagger.Provides

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
@Module
class HomeModelModule {
    @Provides
    fun provideHomeMyReposModel(githubService: GithubService): IHomeMyReposModel {
        return HomeMyReposModel(githubService)
    }
}