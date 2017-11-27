package com.pn.sie.likehub.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pn.sie.likehub.R
import com.pn.sie.likehub.presenter.HomeMyReposPresenter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class MainFragment : DaggerFragment() {

    @Inject private
    lateinit var presenter: HomeMyReposPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.start()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.holdMyRepos(false)
    }

    override fun onResume() {
        super.onResume()
        presenter.holdMyRepos(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.main_fragment, container, false)
}
