/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pn.sie.likehub.di;

import com.pn.sie.likehub.presenter.pieces.ProfilePresenterPiecesModule;
import com.pn.sie.likehub.presenter.pieces.SearchReposPresenterPiecesModule;
import com.pn.sie.likehub.view.activity.MainActivity;
import com.pn.sie.likehub.presenter.pieces.HomePresenterPiecesModule;
import com.pn.sie.likehub.view.activity.ProfileActivity;
import com.pn.sie.likehub.view.activity.SearchReposActivity;
import com.pn.sie.likehub.view.fragment.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module//Android用户层面上的View界面接口
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ActivityScoped//HomePresenterPiecesModule.class将Presenter所需依赖(M和V)单独向外部公开, 以便P层能获取到
    @ContributesAndroidInjector(modules = HomePresenterPiecesModule.class)
    abstract MainFragment mainFragment();//使用P层的起始点, 若注入完毕, P层作为中介者应当管理好View层的各种响应(统一组织)并分发给Model来处理

    @ActivityScoped
    @ContributesAndroidInjector(modules = ProfilePresenterPiecesModule.class)
    abstract ProfileActivity profileActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SearchReposPresenterPiecesModule.class)
    abstract SearchReposActivity searchReposActivity();
}
