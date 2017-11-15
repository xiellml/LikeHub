package com.pn.sie.likehub.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pn.sie.likehub.Navigator
import com.pn.sie.likehub.xutil.LogPrinter

class Start : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogPrinter.d("start..")
        Navigator.INSTANCE.showHome(this)
    }
}
