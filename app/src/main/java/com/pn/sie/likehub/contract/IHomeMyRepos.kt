package com.pn.sie.likehub.contract

import com.pn.sie.likehub.contract.base.IBaseP
import com.pn.sie.likehub.contract.base.IBaseV

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
        fun showMyRepos(isRefresh: Boolean = true)
        fun leaveApp()
    }

    //input/output
    interface IPresenter : IBaseP {
        fun holdMyRepos(isRefresh: Boolean = true)
        fun placeLastOpenTime()
    }
}