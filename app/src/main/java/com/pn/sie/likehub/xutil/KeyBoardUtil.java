package com.pn.sie.likehub.xutil;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * 避免输入法面板遮挡
 * <p>在manifest.xml中activity中设置</p>
 * <p>android:windowSoftInputMode="stateVisible|adjustResize"</p>
 * <p>
 * 参数和方法使用说明:
 * http://blog.csdn.net/ccpat/article/details/46717573
 * </p>
 */

public class KeyBoardUtil {
    /**
     * 打卡软键盘
     *
     * @param mContext  上下文
     * @param mEditText 输入框
     *                  public static final int RESULT_HIDDEN　常量值: 3 (0x00000003)
     *                  <p>
     *                  　　　　showSoftInput(View, int, ResultReceiver)和hideSoftInputFromWindow(IBinder, int, ResultReceiver)中ResultReceiver结果代码标志：软键盘窗口从显示切换到隐藏时的状态。
     *                  <p>
     *                  <p>
     *                  <p>
     *                  public static final int RESULT_SHOWN　常量值: 2 (0x00000002)
     *                  <p>
     *                  　　　　showSoftInput(View, int, ResultReceiver)和hideSoftInputFromWindow(IBinder, int, ResultReceiver)中ResultReceiver结果代码标志：软键盘窗口从隐藏切换到显示时的状态。
     *                  <p>
     *                  <p>
     *                  <p>
     *                  public static final int RESULT_UNCHANGED_HIDDEN　　常量值: 1 (0x00000001)
     *                  <p>
     *                  　　　　showSoftInput(View, int, ResultReceiver)和hideSoftInputFromWindow(IBinder, int, ResultReceiver)中ResultReceiver结果代码标志：软键盘窗口不变保持隐藏时的状态。
     *                  <p>
     *                  <p>
     *                  <p>
     *                  public static final int RESULT_UNCHANGED_SHOWN　常量值: 0 (0x00000000)
     *                  <p>
     *                  　　　　showSoftInput(View, int, ResultReceiver)和hideSoftInputFromWindow(IBinder, int, ResultReceiver)中ResultReceiver结果代码标志：软键盘窗口不变保持显示时的状态。
     *                  <p>
     *                  <p>
     *                  <p>
     *                  public static final int SHOW_FORCED　　　常量值: 2 (0x00000002)
     *                  <p>
     *                  　　　　showSoftInput(View, int)标志，表示用户强制打开输入法（如长按菜单键），一直保持打开直至只有显式关闭。
     *                  <p>
     *                  <p>
     *                  <p>
     *                  public static final int SHOW_IMPLICIT　常量值: 1 (0x00000001)
     *                  <p>
     *                  　　　   showSoftInput(View, int)标志，表示隐式显示输入窗口，非用户直接要求。窗口可能不显示。
     *                  <p>
     *                  和showSoftInput()方法不同的是，使用toggleSoftInput()显示软键盘时，
     *                  并不要求当前界面布局中有一个已经获取焦点的EditText，   即使当前布局是完全空白的，一个View也没有（除了最外层的Layout），
     *                  toggleSoftInput也能够显示软键盘。不过如果没有一个已经获取焦点的EditText，那么软键盘中的按键输入都是无效的
     *                  ==>不能直接在Activity的onCreate()，onResume()，onAttachedToWindow()中使用, 使用postDelayed()50ms+
     */
    public static void openKeyboard(Context mContext, EditText mEditText) {
        if (mEditText == null) return;
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);//接收表示, 不是显示作用
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);//若在Activity的onCreate()调用则延时50ms+让布局加载完成
    }

    /**
     * 关闭软键盘
     *
     * @param activity 上下文
     *                 public static final int HIDE_IMPLICIT_ONLY　常量值: 1 (0x00000001)
     *                 <p>
     *                 　　　　hideSoftInputFromWindow(IBinder, int)中的标志，表示如果用户未显式地显示软键盘窗口，则隐藏窗口。
     *                 <p>
     *                 <p>
     *                 <p>
     *                 public static final int HIDE_NOT_ALWAYS　常量值: 2 (0x00000002)
     *                 <p>
     *                 　　　　hideSoftInputFromWindow(IBinder, int)中的标志，表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
     *                 <p>
     */
    public static void closeKeyboard(Activity activity) {
        if (activity == null) return;
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    /**
     * 输入法是否显示
     */
    public static boolean isKeyboard(EditText edittext) {
        boolean bool = false;
        InputMethodManager imm = (InputMethodManager) edittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive()) {
            bool = true;
        }
        return bool;
    }


    /**
     * 强制显示输入法键盘
     */
    public static void showKeyboard(EditText edit) {
        edit.requestFocus();
        InputMethodManager imm = (InputMethodManager) edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.showSoftInput(edit, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 强制隐藏输入法键盘
     */
    public static void hideKeyboard(EditText edittext) {
        InputMethodManager imm = (InputMethodManager) edittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive()) {
            imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
        }
    }

    /**
     * 如果本页没有EditText(可能前一个页面需要保留显示)
     * 此时采用的隐藏方式
     *
     * @param activity AC
     */
    public static void hideKeyboardWithAc(Activity activity) {
        if (activity == null) return;
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive()) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    /**
     * 切换软键盘的状态(只调用这同一个方法, 否则隐藏可能不起作用[由于HIDE_NOT_ALWAYS])
     * 如当前为收起变为弹出,若当前为弹出变为收起
     */
    public static void toggleKeyboard(EditText edittext) {
        InputMethodManager imm = (InputMethodManager) edittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 通过定时器强制隐藏虚拟键盘
     */
    public static void TimerHideKeyboard(final View v) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
            }
        }, 10);
    }
}
