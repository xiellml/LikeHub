package com.pn.sie.likehub.contract.base;

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 *
 * Description: TODO
 */
//View可以设置Presenter, 所以View层(Fragment)要持有Presenter实例;
//然后在Fragment当中适时调用P层的start等业务逻辑函数(继承PBase以得到更多业务功能)
public interface IBaseV<T>  {
    void setPresenter(T presenter);
}