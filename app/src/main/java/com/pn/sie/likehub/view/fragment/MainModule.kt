package com.pn.sie.likehub.view.fragment

import com.pn.sie.likehub.contract.IHomeMyRepos
import com.pn.sie.likehub.di.ActivityScoped
import com.pn.sie.likehub.di.FragmentScoped
import com.pn.sie.likehub.presenter.HomeMyReposPresenter

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
@Module
abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun mainFragment(): MainFragment

    @ActivityScoped
    @Binds
    internal abstract fun homeReposPresenter(presenter: HomeMyReposPresenter): IHomeMyRepos.IPresenter
}