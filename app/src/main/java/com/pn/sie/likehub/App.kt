package com.pn.sie.likehub

import android.support.multidex.MultiDexApplication
import com.pn.sie.likehub.xutil.LogPrinter

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        testFunc()
    }

    private fun testFunc() {
        if (BuildConfig.DEBUG) {
            LogPrinter.enableRel(2, true)
            LogPrinter.addUserLogger("Sie")
            LogPrinter.takeOver("Sie")
        }
    }
}