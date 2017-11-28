package com.pn.sie.likehub.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pn.sie.likehub.App
import com.pn.sie.likehub.Navigator
import com.pn.sie.likehub.xutil.LogPrinter

class Start : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogPrinter.d("start..")
        App.gate(stringFromJNI())
        Navigator.INSTANCE.showHome(this)
    }

    external fun stringFromJNI(): String

    companion object {
        init {
            //Used to load the 'native-lib' library on application startup.
            //when does lib load? this activity
            System.loadLibrary("native-lib")
        }
    }
}
