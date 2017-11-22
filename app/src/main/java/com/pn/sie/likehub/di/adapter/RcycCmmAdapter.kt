package com.pn.sie.likehub.di.adapter

import android.content.Context

import com.pn.sie.likehub.di.adapter.basic.BaseRcycMulTypeAdapter
import com.pn.sie.likehub.di.adapter.basic.ItemViewDelegate
import com.pn.sie.likehub.di.adapter.basic.RcycViewHolder

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * TODO attachToRoot http://blog.csdn.net/u012702547/article/details/52628453
 * TODO 分割线等等: http://tangpj.com/2017/02/17/recyclerviewutils/ http://www.jianshu.com/p/3c454ec8c2d6
 * TODO 点击事件: 1. view.findViewById();view.setListener() <- Rv.addOnItemTouchListener().onItemClick();
 * todo          2. onBindViewHolder(){}->new IView.OnClickListener()}
 * todo          3. new RecyclerView.OnChildAttachStateChangeListener(){ onChildViewAttachedToWindow(IView subView)}
 * 4. 侧滑 https://github.com/yanzhenjie/SwipeRecyclerView
 * https://github.com/h6ah4i/RecyclerViewiOSMailAppLikeSwipe   https://github.com/mcxtzhang/SwipeDelMenuLayout
 * https://github.com/nikhilpanju/RecyclerViewEnhanced mRecyclerView.addOnItemTouchListener(onTouchListener);
 * Description: 通用rcyc adapter
 */
abstract class RcycCmmAdapter<T> : BaseRcycMulTypeAdapter<T> {

    protected var mLayoutId: Int = 0//子类可以使用

    //务必使用convert()T t 数据项来进行刷新view
    protected constructor(context: Context, datas: List<T>, layoutId: Int) : super(context, datas) {
        mLayoutId = layoutId

        addItemViewDelegate(object : ItemViewDelegate<T> {
            override fun getItemViewLayoutId(): Int {
                return layoutId
            }

            override fun isForViewType(item: T, position: Int): Boolean {
                return true
            }

            override fun convert(holder: RcycViewHolder, t: T, position: Int) {
                this@RcycCmmAdapter.convert(holder, t, position)
            }
        })
    }

    constructor(context: Context, datas: List<T>) : super(context, datas) {
        addViewType()
    }

    //rv必须调用setLayoutManager, 否则视图可能不显示
    protected open fun addViewType() {}

    protected abstract fun convert(holder: RcycViewHolder, t: T, position: Int)
}
