package com.pn.sie.likehub.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pn.sie.likehub.R
import com.pn.sie.likehub.contract.IProfileUserInfo
import com.pn.sie.likehub.model.entity.User
import com.pn.sie.likehub.presenter.ProfileUserInfoPresenter
import com.pn.sie.likehub.xutil.LogPrinter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.profile_activity.*
import javax.inject.Inject

class ProfileActivity : DaggerAppCompatActivity(), IProfileUserInfo.IView {

    @Inject
    lateinit var presenter: ProfileUserInfoPresenter

    override fun showUserInfo(data: User?) {
        LogPrinter.json(data)
        em_tv.setText(data.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        presenter.holdUserInfo(this)
    }

    companion object {
        fun buildIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }
}
