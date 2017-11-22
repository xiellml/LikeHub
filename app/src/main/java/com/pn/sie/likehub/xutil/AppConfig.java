package com.pn.sie.likehub.xutil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: 添加其他配置http://www.jianshu.com/p/72494773aace
 */
/*
概念(注: PPI可能与DPI混用):
1. 分辨率: 通常指的是视频/电子屏幕的分辨率, 用每个方向上的像素数量来表示(实际的手持设备的尺寸单位是英尺),因此也用每英尺所拥有的像素表示--PPI;
          而显示分辨率值得是打印品类的分辨率,用每英寸所含网点数(点或像素)来衡量--DPI.
2. 倍率: 不同分辨率的倍数关系,基于160PPI(中屏幕)
3. Android源码密度: densityDpi == ppi; density == 倍率
 */
public class AppConfig {

    private static DisplayMetrics metrics;
    private volatile static AppConfig instance;

    private AppConfig(Context appContext) {
        metrics = appContext.getResources().getDisplayMetrics();
        //小米2S=2倍屏,4.3inch; 华为P8=3倍屏,5.2inch
        //LogPrinter.d("density倍率(请使用对应设计图,如: 720P对应Mi 2S) -- " + metrics.density);//==2
        //LogPrinter.d("dp/10px (请使用对应设计图,如: 720P对应Mi 2S) -- " + px2dip(10));//5
    }

    public static void with(Context context) {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig(context.getApplicationContext());
                }
            }
        }
    }

    //----------TODO 签名信息------------------------------------------------------------------------------------
    private static boolean isSpace(String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取App签名
     *
     * @param packageName 包名
     * @return App签名
     */
    public static Signature[] getAppSignature(Context context, String packageName) {
        if (isSpace(packageName)) return null;
        try {
            PackageManager pm = context.getPackageManager();
            @SuppressLint("PackageManagerGetSignatures")
            PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return pi == null ? null : pi.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取应用签名的的SHA1值
     * <p>可据此判断高德，百度地图key是否正确</p>
     *
     * @param packageName 包名
     * @return 应用签名的SHA1字符串, 比如：53:FD:54:DC:19:0F:11:AC:B5:22:9E:F1:1A:68:88:1B:8B:E8:54:42
     */
    public static String getAppSignatureSHA1(Context context, String packageName) {
        Signature[] signature = getAppSignature(context, packageName);
        if (signature == null) return null;
        //别用, 有问题
        return Digest.hashHexVal(signature[0].toByteArray(), "SHA1").
                replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    }

    //----------TODO 尺寸信息------------------------------------------------------------------------------------
    public static int deviceWidth() {
        return metrics == null ? 720 : metrics.widthPixels;
    }

    public static int deviceHeight() {
        return metrics == null ? 720 : metrics.heightPixels;
    }

    public static float deviceDensity() {
        return metrics == null ? 2.0f : metrics.density;
    }

    /**
     * Whether the Status bar is hidden or not,the method always helps you get
     * the height of Status bar.
     * 获得状态栏的高度
     * todo statusBar: XiaoMi-2S: 50px,HuaWei-P8: 72px
     *
     * @param context The context to use. Usually your
     *                {@link android.app.Application} or
     *                {@link android.app.Activity} object.
     * @return Return the height of Status bar.
     */
    public static int getStatusHeight(Context context) {
        int statusHeight;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        statusHeight = context.getResources().getDimensionPixelSize(resId);
        return statusHeight;
    }

    public static boolean isNavigationBarShow(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (menu || back) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static int getNavigationBarHeight(Activity activity) {
        if (!isNavigationBarShow(activity)) {
            return 0;
        }
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        //获取NavigationBar的高度
        return resources.getDimensionPixelSize(resourceId);
    }
    //----------TODO 尺寸转换------------------------------------------------------------------------------------

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * 160ppi=>mdpi
     * 小米2S[720P,4.3inch,1280*720]:320ppi=>xhdpi
     * 华为p8[1080P,5.2,1920*1080]:480ppi=>xxhdpi
     * Nexus 6p[1440P,5.7,1440*2560]:600ppi=>xxxhdpi
     * final float ppi = context.getResources().getDisplayMetrics().densityDpi;
     * density = densityDpi/160;
     */
    public static int dip2px(float dpValue) {
        return metrics == null ? (int) dpValue : (int) (dpValue * metrics.density + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        return metrics == null ? (int) pxValue : (int) (pxValue / metrics.density + 0.5f);
    }

    //----------TODO 版本信息------------------------------------------------------------------------------------
    //版本名
    public static String getVersionName(Context context) {
        String verName = null;
        try {
            PackageManager pm = context.getApplicationContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getApplicationContext().getPackageName(), PackageManager.GET_CONFIGURATIONS);
            verName = pi.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verName == null ? "unknown" : verName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        int verNum = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            verNum = pi.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verNum;
    }

    //----------TODO 屏幕截图------------------------------------------------------------------------------------

    /**
     * 截屏
     *
     * @param activity         上下文
     * @param includeStatusBar 是否包括状态栏
     * @return 图像数据
     */
    public static Bitmap snapShot(Activity activity, boolean includeStatusBar) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();

        int width = deviceWidth();
        int height = deviceHeight();
        Bitmap bp = null;
        if (includeStatusBar) {
            bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        } else {
            Rect frame = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            int statusBarHeight = frame.top;
            bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        }
        view.destroyDrawingCache();
        return bp;
    }
}
