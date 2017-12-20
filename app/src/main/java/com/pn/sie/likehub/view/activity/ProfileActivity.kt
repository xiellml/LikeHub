package com.pn.sie.likehub.view.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pn.sie.likehub.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
    }


    companion object {
        fun buildIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }
}
