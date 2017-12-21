package com.pn.sie.likehub.presenter.pieces

import com.pn.sie.likehub.api.GithubService
import com.pn.sie.likehub.contract.IProfileUserInfo
import com.pn.sie.likehub.model.IProfileUserInfoModel
import com.pn.sie.likehub.model.impl.ProfileUserInfoModel
import com.pn.sie.likehub.view.activity.ProfileActivity
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
class ProfilePresenterPiecesModule {
    @Provides
    fun modelProvider(githubService: GithubService): IProfileUserInfoModel {
        return ProfileUserInfoModel(githubService)
    }

    @Provides
    fun viewProvider(activity: ProfileActivity): IProfileUserInfo.IView {
        return activity
    }
}