## 项目in progress
- [ ] 查询 [API][queryLink]
- [ ] GoogleSample 1: [ToDo][todoRepoLink] (MVP& Kotlin& Dagger) 
- [ ] GoogleSample 2: [GithubBrowser][browserRepoLink] (项目分析学习) 
    
    
## 组件
- [x] dagger
- [x] retrofit
- [x] kotlin-android-extensions(or DataBindingUtil)
- [ ] mockito or espresso[地址][espressoLink]
    

Retrofit:
1. 创建本App的Service接口, 包含同一个域名下所有需要组合的的函数方法功能实现
2. [不必需]可以将Service接口中的方法分开成不同对象, 分别组建相关联的Repository对象存储库(用来处理某一分类对象);
   如有本地化的需要, 在此处可以构建二级缓存模式提供仓库的资源数据(GoogleSamples的GithubBrowserSample采用了LiveData与Retrofit框架处理本地化和远程资源)
3. Retrofit.Builder().build().create(xx)构建出Service的实现类的实例(GoogleSample编写了GithubServiceTest类测试,是一个完整的逻辑)


注: 
 - 1)报错指南: 
   * http://www.jianshu.com/p/1b98d0a0e42d
         
 - 2)dagger参考:
   * https://github.com/a1018875550/ZhihuDaily
   * https://github.com/ChineseLincoln/Dagger2Mvp
   * http://blog.csdn.net/a1018875550/article/details/73332594
   * http://blog.csdn.net/u012804379/article/details/51076617
   * http://blog.csdn.net/lihenair/article/details/51424942
         
 - 3)Room参考:
   * room: http://www.jianshu.com/p/29e5e8c75450
         
         
         
         
         
         
         
         
         
         
         
   [queryLink]: <https://github.com/Zane96/GithubQuery.git>
   [todoRepoLink]: <https://github.com/googlesamples/android-architecture/tree/todo-mvp-dagger/todo.md>
   [browserRepoLink]: <http://www.jianshu.com/p/c0ed0bc45ab6>
   [espressoLink]: <http://www.jianshu.com/p/a856ea119d11>