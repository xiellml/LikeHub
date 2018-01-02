package com.pn.sie.likehub.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import com.pn.sie.likehub.App
import com.pn.sie.likehub.Navigator
import com.pn.sie.likehub.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            Navigator.INSTANCE.sendRepoToEmailBox(this, "https://github.com/" + PreferenceManager
                    .getDefaultSharedPreferences(App.self)
                    .getString("example_text", ""))
        }
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

            R.id.action_search -> {
                Navigator.INSTANCE.navigateToSearch(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun buildIntent(context: Context): Intent? {
            return Intent(context, MainActivity::class.java)
        }
    }
}