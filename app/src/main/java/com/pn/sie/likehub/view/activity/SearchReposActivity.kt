package com.pn.sie.likehub.view.activity

import android.os.Bundle
import com.pn.sie.likehub.R
import dagger.android.support.DaggerAppCompatActivity

class SearchReposActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_repos_activity)
    }
}
