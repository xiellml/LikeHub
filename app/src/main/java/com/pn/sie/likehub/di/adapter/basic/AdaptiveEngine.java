package com.pn.sie.likehub.di.adapter.basic;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;

import com.pn.sie.likehub.xutil.AppConfig;

/**
 * @version v1.1
 *          1.宽度适配 {@link #widthAdaptive}
 *          2.高度适配 {@link #heightAdaptive}
 *          3.margin适配 {@link #marginAdaptive}
 *          4.padding适配 {@link #paddingAdaptive}
 *          5.文字大小适配 {@link #textSizeAdaptive}
 *          注意: 必须可以获取父元素,否则会报错.如右边这个View不可以(parent=null): inflate(R.layout.flow_tag_item, null);
 */
public class AdaptiveEngine {

    //XIAO_MI 2s
    public static final int REF_WIDTH_720 = 720;
    //HUA_WEI p8
    public static final int REF_WIDTH_1080 = 1080;

    /**
     * 宽度适配
     *
     * @param refWidth  设计图参考的手机宽度,如:720
     * @param widDesign 设计值
     * @param widgets   控件
     */
    public static void widthAdaptive(int refWidth, double widDesign, View... widgets) {
        for (View view : widgets) {
            view.getLayoutParams().width = (int) (AppConfig.deviceWidth() / (refWidth / widDesign));
        }
    }

    /**
     * 高度适配
     *
     * @param width
     * @param heiDesign
     * @param widgets
     */
    public static void heightAdaptive(int width, double heiDesign, View... widgets) {
        for (View view : widgets) {
            view.getLayoutParams().height = (int) (AppConfig.deviceWidth() / (width / heiDesign));
        }

    }

    /**
     * 间隔适配
     *
     * @param refWidth 设计图参考的手机宽度,如:720
     * @param left     左边距
     * @param top      上边距
     * @param right    右边距
     * @param bottom   底边距
     * @param panels   控件
     */
    public static void marginAdaptive(int refWidth, int left, int top, int right, int bottom, View... panels) {
        if (0 != left) {
            float l = left * AppConfig.deviceDensity() + 0.5f;//px->dp(视作PW的dp)
            float l2 = AppConfig.deviceWidth() / (refWidth / l);//屏幕大小不同时的dp(SW的dp)
            left = (int) (l2 / AppConfig.deviceDensity() + 0.5f);//pd->px(SW的px)
        }
        if (0 != top) {
            float t = top * AppConfig.deviceDensity() - 0.5f;
            float t2 = AppConfig.deviceWidth() / (refWidth / t);
            top = (int) (t2 / AppConfig.deviceDensity() - 0.5f);
        }
        if (0 != right) {
            float r = right * AppConfig.deviceDensity();
            float r2 = AppConfig.deviceWidth() / (refWidth / r);
            right = (int) (r2 / AppConfig.deviceDensity());
        }
        if (0 != bottom) {
            float b = bottom * AppConfig.deviceDensity();
            float b2 = AppConfig.deviceWidth() / (refWidth / b);
            bottom = (int) (b2 / AppConfig.deviceDensity());
        }
        for (View view : panels) {
            MarginLayoutParams params = (MarginLayoutParams) view
                    .getLayoutParams();
            params.setMargins(left, top, right, bottom);
        }
    }

    /**
     * 内距适配
     */
    public static void paddingAdaptive(int left, int top, int right, int bottom, View... panel) {
        for (View view : panel) {
            view.setPadding(left, top, right, bottom);
        }
    }

    /**
     * 字号适配
     *
     * @param refWidth  设计稿参考的手机宽度,如:720
     * @param textSize  设计稿中文字大小
     * @param textViews 文字控件
     */
    public static void textSizeAdaptive(int refWidth, int textSize, TextView... textViews) {
        float value = AppConfig.deviceWidth() / (refWidth / textSize);
        for (TextView text : textViews) {
            text.setTextSize(TypedValue.COMPLEX_UNIT_PX, value);
        }
    }
}
