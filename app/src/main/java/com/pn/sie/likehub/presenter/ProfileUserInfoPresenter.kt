package com.pn.sie.likehub.presenter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.annotation.NonNull
import com.pn.sie.likehub.contract.IProfileUserInfo
import com.pn.sie.likehub.di.ActivityScoped
import com.pn.sie.likehub.model.IProfileUserInfoModel
import com.pn.sie.likehub.model.impl.ProfileUserInfoModel
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
@ActivityScoped
class ProfileUserInfoPresenter @Inject constructor(var model: IProfileUserInfoModel, var view: IProfileUserInfo.IView) : IProfileUserInfo.IPresenter {
    override fun start() {
    }

    override fun holdUserInfo(@NonNull owner: LifecycleOwner) {
        model.getUserInfo().observe(owner, Observer { data ->
            view.showUserInfo(data?.body)
        })
    }
}