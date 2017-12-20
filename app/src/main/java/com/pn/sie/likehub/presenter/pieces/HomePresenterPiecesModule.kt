package com.pn.sie.likehub.presenter.pieces

import com.pn.sie.likehub.api.GithubService
import com.pn.sie.likehub.contract.IHomeMyRepos
import com.pn.sie.likehub.model.IHomeMyReposModel
import com.pn.sie.likehub.model.impl.HomeMyReposModel
import com.pn.sie.likehub.view.fragment.MainFragment
import dagger.Module
import dagger.Provides

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description:
 * 提供View和Model的模块(Module),
 * 依附到View层(@ContributesAndroidInjector(modules = HomePresenterPiecesModule.class) abstract MainFragment mainFragment()),
 * 并且将其注入注入给Presenter.
 * </p>
 */
@Module//Home首页; Presenter中介者; Pieces所要依赖的对象组件; Module模块
class HomePresenterPiecesModule {
    @Provides
    fun modelProvider(githubService: GithubService): IHomeMyReposModel {
        return HomeMyReposModel(githubService)
    }

    @Provides
    fun viewProvider(fragment: MainFragment): IHomeMyRepos.IView {
        return fragment
    }
}