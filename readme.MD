## todo lists
- [ ] 分析项目: http://www.jianshu.com/p/c0ed0bc45ab6
- [ ] 查询 [API][queryLink]
- [ ] Google Sample
- [ ] MVP&Kotlin: http://blog.csdn.net/a1018875550/article/details/73332594
- [ ] room: http://www.jianshu.com/p/29e5e8c75450
    
    ### 组件
    - [ ] dagger
    - [ ] retrofit
    - [ ] mockito or espresso[地址][espressoLink]
    - [ ] DataBindingUtil or kotlin-android-extensions
    

Retrofit:
1. 创建本App的Service接口, 包含同一个域名下所有需要组合的的函数方法功能实现
2. [不必需]可以将Service接口中的方法分开成不同对象, 分别组建相关联的Repository对象存储库(用来处理某一分类对象);
   如有本地化的需要, 在此处可以构建二级缓存模式提供仓库的资源数据(GoogleSamples的GithubBrowserSample采用了LiveData与Retrofit框架处理本地化和远程资源)
3. Retrofit.Builder().build().create(xx)构建出Service的实现类的实例(GoogleSample编写了GithubServiceTest类测试,是一个完整的逻辑)


注: 报错指南http://www.jianshu.com/p/1b98d0a0e42d
   [queryLink]: <https://github.com/Zane96/GithubQuery.git>
   [espressoLink]: <http://www.jianshu.com/p/a856ea119d11>