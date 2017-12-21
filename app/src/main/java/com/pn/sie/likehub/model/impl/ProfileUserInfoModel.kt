package com.pn.sie.likehub.model.impl

import android.arch.lifecycle.LiveData
import android.preference.PreferenceManager
import com.pn.sie.likehub.App
import com.pn.sie.likehub.api.ApiResponse
import com.pn.sie.likehub.api.GithubService
import com.pn.sie.likehub.model.IProfileUserInfoModel
import com.pn.sie.likehub.model.entity.User
import javax.inject.Inject

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
class ProfileUserInfoModel @Inject constructor(var githubService: GithubService): IProfileUserInfoModel {
    override fun getUserInfo(): LiveData<ApiResponse<User>> {
        return githubService.getUser(PreferenceManager
                .getDefaultSharedPreferences(App.self)
                .getString("example_text", "sieml"))
    }
}