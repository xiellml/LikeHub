package com.pn.sie.likehub.di.adapter.basic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * ListView,GridView
 */
public class RcycViewHolder extends RecyclerView.ViewHolder  {

    //会存下所有的子view索引,因为onCreateViewHolder每次会生成一个Holder(复用不复用内部机制解决)
    private SparseArray<View> mViews;//根据id读写界面item的子view
    private Context mContext;//上下文

    //私有构造
    private RcycViewHolder(Context context, View itemView) {
        super(itemView);//  holder.itemView
        mContext = context;
        mViews = new SparseArray<>();
    }

    //对外公开静态实例化Holder
    public static RcycViewHolder createViewHolder(Context context, View itemView) {
        return new RcycViewHolder(context, itemView);
    }

    /*public IView mViewContent;

    public void setSwipeContentView(int id) {
        mViewContent = itemView.findViewById(id);
    }
    private ItemTouchHelperExtension mItemTouchHelperExtension;

    public void setItemTouchHelperExtension(ItemTouchHelperExtension itemTouchHelperExtension) {
        if (mItemTouchHelperExtension == null) mItemTouchHelperExtension = itemTouchHelperExtension;
        itemView.setOnTouchListener(new IView.OnTouchListener() {
            @Override
            public boolean onTouch(IView v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mItemTouchHelperExtension.startDrag(RcycViewHolder.this);
                }
                return true;
            }
        });
    }

    public ItemTouchHelperExtension getItemTouchHelperExtension() {
        return mItemTouchHelperExtension;
    }

    public void closeSwipeOpen() {
        if (mItemTouchHelperExtension != null) mItemTouchHelperExtension.closeOpened();
    }*/

    public void setSubViewClickListener(View subView, final OnViewClickListener listener) {
        //if (subView == null) return;
        subView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    //LogPrinter.d("pos click -- " + getAdapterPosition());
                    listener.onViewClick(view, getAdapterPosition());
                }
            }
        });
    }

    public void setSubViewLongPressListener(View subView, final OnViewClickListener listener) {
        //if (subView == null) return;
        subView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (listener != null) {
                    //LogPrinter.d("pos click -- " + getAdapterPosition());
                    listener.onViewClick(view, getAdapterPosition());
                }
                return false;
            }
        });
    }

    public View hasOrNotSubView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            if (view != null) mViews.put(viewId, view);
        }
        return view;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return itemView;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public RcycViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public RcycViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        //noinspection deprecation
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    @SuppressLint("NewApi")
    public RcycViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public RcycViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    public RcycViewHolder setGone(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public RcycViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public RcycViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public RcycViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public RcycViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public RcycViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public RcycViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public RcycViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public RcycViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public RcycViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public RcycViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    public RcycViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public RcycViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public RcycViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public RcycViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public RcycViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public RcycViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public interface OnViewClickListener {
        void onViewClick(View v, int adapterPosition);
    }
}