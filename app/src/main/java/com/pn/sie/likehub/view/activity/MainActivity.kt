package com.pn.sie.likehub.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.pn.sie.likehub.Navigator
import com.pn.sie.likehub.R
import com.pn.sie.likehub.xutil.LogPrinter
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            Navigator.INSTANCE.sendRepoToEmailBox(this, "https://faked.url")
        }

        LogPrinter.d(stringFromJNI())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Navigator.INSTANCE.navigateToSettings(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    external fun stringFromJNI(): String

    companion object {
        init {
            //Used to load the 'native-lib' library on application startup.
            //when does lib load? this activity
            System.loadLibrary("native-lib")
        }

        fun buildIntent(context: Context): Intent? {
            return Intent(context, MainActivity::class.java)
        }
    }
}