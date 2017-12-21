package com.pn.sie.likehub.presenter.pieces

import com.pn.sie.likehub.api.GithubService
import com.pn.sie.likehub.model.ISearchReposModel
import com.pn.sie.likehub.model.impl.SearchReposModel
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
class SearchReposPresenterPiecesModule {
    @Provides
    fun modelProvider(githubService: GithubService): ISearchReposModel {
        return SearchReposModel(githubService)
    }
}