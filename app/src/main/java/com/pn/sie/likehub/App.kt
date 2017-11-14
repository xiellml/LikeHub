package com.pn.sie.likehub

import android.support.multidex.MultiDexApplication
import timber.log.Timber

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
            Timber.plant(Timber.DebugTree())
        }
    }
}