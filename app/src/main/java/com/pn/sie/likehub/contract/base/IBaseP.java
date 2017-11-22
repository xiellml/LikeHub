
package com.pn.sie.likehub.contract.base;

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 *
 * Description: TODO
 */
//定义分离出的View后台业务逻辑函数start等等, Presenter的实现类的构造函数会传入View,所以也会持有Fragment,然后在Presenter实现类也会持有Model类的实现类.
//延伸: 再在Presenter实现类书写的对外公开方法中调用Model类函数, 此函数会有回调callback, 最后在callback中调用View的UI函数即可.
public interface IBaseP {
    void start();//执行数据运算
}