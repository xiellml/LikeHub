package com.pn.sie.likehub

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.pn.sie.likehub.view.activity.MainActivity
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
        data.data = Uri.parse("mailto:siesielee@gmail.com")
        data.putExtra(Intent.EXTRA_SUBJECT, "RePost Github Repo")
        data.putExtra("text", "Valued Repo: " + url)
        context.startActivity(data)
    }

    fun showHome(context: Context) {
        context.startActivity(MainActivity.buildIntent(context))
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