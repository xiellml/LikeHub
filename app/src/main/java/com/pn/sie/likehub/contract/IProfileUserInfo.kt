package com.pn.sie.likehub.contract

import android.arch.lifecycle.LifecycleOwner
import android.support.annotation.NonNull
import com.pn.sie.likehub.contract.base.IBasePresenter
import com.pn.sie.likehub.contract.base.IBaseView
import com.pn.sie.likehub.model.entity.Repo
import com.pn.sie.likehub.model.entity.User

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
interface IProfileUserInfo {
    interface IView : IBaseView<IBasePresenter> {
        fun showUserInfo(data: User?)
    }

    interface IPresenter : IBasePresenter {
        fun holdUserInfo(@NonNull owner: LifecycleOwner)
    }
}