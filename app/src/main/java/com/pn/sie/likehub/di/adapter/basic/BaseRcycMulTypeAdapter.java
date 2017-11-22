package com.pn.sie.likehub.di.adapter.basic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseRcycMulTypeAdapter<T> extends RecyclerView.Adapter<RcycViewHolder> {

    protected List<T> mData;
    protected Context mContext;
    protected ItemViewDelegateManager<T> mItemViewDelegateManager;

    private boolean isAdaptive = false;//是否需要代码适配ItemView
    private float mWidth;//宽度
    private float mHeight;//高度

    public BaseRcycMulTypeAdapter(Context context, List<T> datas) {
        mContext = context;
        mData = datas;
        mItemViewDelegateManager = new ItemViewDelegateManager<T>();
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mData.get(position), position);
    }


    @Override
    public RcycViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        View item = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        if (isAdaptive) {
            AdaptiveEngine.widthAdaptive(AdaptiveEngine.REF_WIDTH_720, mWidth * 2, item);
            AdaptiveEngine.heightAdaptive(AdaptiveEngine.REF_WIDTH_720, mHeight * 2, item);
        }
        RcycViewHolder holder = RcycViewHolder.createViewHolder(mContext, item);
        try {
            setListener(holder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holder;
    }

    public void setEnableAdaptive(int width, int height) {
        isAdaptive = true;
        mWidth = width;
        mHeight = height;
    }

    public abstract void setListener(RcycViewHolder holder);

    public void convert(RcycViewHolder holder, T t) {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    @Override
    public void onBindViewHolder(RcycViewHolder holder, int position) {
        convert(holder, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public List<T> getDatas() {
        return mData;
    }

    public BaseRcycMulTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public BaseRcycMulTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }
}
