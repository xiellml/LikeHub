package com.pn.sie.likehub.model

import com.pn.sie.likehub.model.callbak.LoadReposCallback

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
interface IHomeMyReposModel {

    fun getServerMyRepos(isRefresh: Boolean = true, callback: LoadReposCallback)
}