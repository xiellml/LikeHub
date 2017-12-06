package com.pn.sie.likehub.di;

import com.pn.sie.likehub.model.IHomeMyReposModel;
import com.pn.sie.likehub.model.impl.HomeMyReposModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

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
abstract public class ModelsModule {

    @Singleton
    @Binds
    abstract IHomeMyReposModel provideHomeMyReposModel(HomeMyReposModel homeMyReposModel);
}
