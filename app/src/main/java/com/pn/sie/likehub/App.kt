package com.pn.sie.likehub

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.pn.sie.likehub.di.AppInjector
import com.pn.sie.likehub.xutil.LogPrinter
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
class App : MultiDexApplication(), HasActivityInjector {

    @Inject
    private lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        testFunc()
    }

    companion object {
        var enter = "_"

        fun gate(value: String) {
            enter = value
        }

        fun entered(): String {
            return enter
        }
    }

    private fun testFunc() {
        if (BuildConfig.DEBUG) {
            LogPrinter.enableRel(2, true)
            LogPrinter.addUserLogger("Sie")
            LogPrinter.takeOver("Sie")
        }
    }
}