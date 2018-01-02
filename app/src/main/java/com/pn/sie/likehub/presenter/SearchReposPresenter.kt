package com.pn.sie.likehub.presenter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.annotation.NonNull
import com.pn.sie.likehub.contract.ISearchRepos
import com.pn.sie.likehub.di.ActivityScoped
import com.pn.sie.likehub.model.ISearchReposModel
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
class SearchReposPresenter @Inject constructor(var model: ISearchReposModel, var view: ISearchRepos.IView) : ISearchRepos.IPresenter {

    override fun holdFoundRepos(@NonNull owner: LifecycleOwner, query: String) {
        model.getSearchRepos(query).observe(owner, Observer { data ->
            //todo next NetworkBoundResource涉及本地数据库
            view.showFoundRepos(data?.body)
        })
    }

    override fun start() {
    }
}