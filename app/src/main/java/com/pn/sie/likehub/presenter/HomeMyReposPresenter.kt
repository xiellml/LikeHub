package com.pn.sie.likehub.presenter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.annotation.NonNull
import com.pn.sie.likehub.api.ApiResponse
import com.pn.sie.likehub.contract.IHomeMyRepos
import com.pn.sie.likehub.di.ActivityScoped
import com.pn.sie.likehub.model.IHomeMyReposModel
import com.pn.sie.likehub.model.entity.Repo
import com.pn.sie.likehub.xutil.LogPrinter
import javax.inject.Inject

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: 构造函数处标识@Inject表明是依赖件, 并且参数处注入两个接口组件, 将会以Provider形式创建它们的实例
 * TODO: 构造方法中的参数使用了接口, 在实际注入时可以使用对应实现类由DaggerXxxComponent.Builder()注入
 * </p>
 */
@ActivityScoped
class HomeMyReposPresenter @Inject constructor(var model: IHomeMyReposModel?, var view: IHomeMyRepos.IView?) : IHomeMyRepos.IPresenter {

    override fun start() {
        //创建Fg实例开始时
    }

    override fun holdMyRepos(@NonNull owner: LifecycleOwner, isRefresh: Boolean) {
        //持有数据, 进行Data-View绑定
        model?.getServerMyRepos(isRefresh)?.observe(owner, Observer { data ->
            LogPrinter.d("data is null $data; " + data?.body)
            view?.showMyRepos(isRefresh, data?.body)//data省略时, 使用it
        })
    }

    override fun placeLastOpenTime() {
        //存放数据到本地, 此次离开App的时刻
    }

    fun destroy() {
        model = null
        view = null
    }
}