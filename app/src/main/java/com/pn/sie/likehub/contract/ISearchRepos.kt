package com.pn.sie.likehub.contract

import android.arch.lifecycle.LifecycleOwner
import android.support.annotation.NonNull
import com.pn.sie.likehub.contract.base.IBasePresenter
import com.pn.sie.likehub.contract.base.IBaseView

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
interface ISearchRepos {
    interface IView : IBaseView<IBasePresenter> {
        fun showFoundRepos()
    }

    interface IPresenter : IBasePresenter {
        fun holdFoundRepos(@NonNull owner: LifecycleOwner, query: String)
    }
}