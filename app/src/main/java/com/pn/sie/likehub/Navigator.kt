package com.pn.sie.likehub

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.pn.sie.likehub.view.activity.MainActivity
import com.pn.sie.likehub.view.activity.ProfileActivity
import com.pn.sie.likehub.view.activity.SettingsActivity

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
enum class Navigator {

    INSTANCE;

    fun sendRepoToEmailBox(context: Context, url: String) {
        val data = Intent(Intent.ACTION_SENDTO)
        data.data = Uri.parse("mailto:siesielee@gmail.com")//or i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
        data.putExtra(Intent.EXTRA_SUBJECT, "Send Github site")
        data.putExtra(Intent.EXTRA_TEXT, "I want to follow u, and this is my github-> " + url)
        context.startActivity(data)
    }

    fun showHome(context: Context) {
        //比对enter值, 正确则进入MainActivity, 否则进入SignInActivity然后再回到MainActivity
        context.startActivity(MainActivity.buildIntent(context))
    }

    fun navigateToProfile(context: Context) {
        context.startActivity(ProfileActivity.buildIntent(context))
    }

    fun navigateToSettings(context: Context) {
        val intent = SettingsActivity.buildIntent(context)
        intent.putExtra("from", "mine_fm")
        context.startActivity(intent)
    }

    fun navigateToSettingsV1(context: Context, sum: Int?) {
        val intent = SettingsActivity.buildIntent(context)
        intent.putExtra("from", "mine_fm")
        intent.putExtra("new_notify_num", sum)
        context.startActivity(intent)
    }
}