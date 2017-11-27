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

import android.app.Application;

import com.pn.sie.likehub.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,//四大组件的Map集合: 用来储存四大组件及其注入器(K-V: Dagger系统注入逻辑)
        AppModule.class,//全局共享的服务: 数据库及表/ServiceAPI(共有实例)
        ActivityBindingModule.class//Activity(MVP分离层级在这里做文章)
})
public interface AppComponent {
    void inject(App githubApp);
    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }
}
