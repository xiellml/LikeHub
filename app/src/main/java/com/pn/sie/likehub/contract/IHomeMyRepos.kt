package com.pn.sie.likehub.contract

import android.arch.lifecycle.LifecycleOwner
import android.support.annotation.NonNull
import com.pn.sie.likehub.contract.base.IBaseP
import com.pn.sie.likehub.contract.base.IBaseV
import com.pn.sie.likehub.model.entity.Repo

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
interface IHomeMyRepos {
    interface IView : IBaseV<IPresenter> {
        fun showMyRepos(isRefresh: Boolean = true, data: List<Repo>?)
        fun leaveApp()
    }

    //input/output
    interface IPresenter : IBaseP {
        fun holdMyRepos(@NonNull owner: LifecycleOwner, isRefresh: Boolean = true)
        fun placeLastOpenTime()
    }
}