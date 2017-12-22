package com.pn.sie.likehub.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.pn.sie.likehub.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.search_repos_activity.*

class SearchReposActivity : DaggerAppCompatActivity(), TextView.OnEditorActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_repos_activity)
    }

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
        var ret = false
        if (actionId == EditorInfo.IME_ACTION_SEARCH || event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
            //发起搜索
            val skWordStr = query_txt_tidt.getText().toString()//name
            if (skWordStr.length > 0) {
                //start search action
                ret = true
            }
        }
        return ret
    }

    companion object {

        fun buildIntent(context: Context): Intent {
            return Intent(context, SearchReposActivity::class.java)
        }
    }
}
