package com.pn.sie.likehub.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.pn.sie.likehub.R
import com.pn.sie.likehub.contract.ISearchRepos
import com.pn.sie.likehub.di.adapter.RcycCmmAdapter
import com.pn.sie.likehub.di.adapter.basic.ItemViewDelegate
import com.pn.sie.likehub.di.adapter.basic.RcycViewHolder
import com.pn.sie.likehub.model.entity.Repo
import com.pn.sie.likehub.model.entity.RepoSearchResponse
import com.pn.sie.likehub.presenter.SearchReposPresenter
import com.pn.sie.likehub.xutil.KeyBoardUtil
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.search_repos_activity.*
import java.util.*
import javax.inject.Inject

class SearchReposActivity : DaggerAppCompatActivity(), ISearchRepos.IView, TextView.OnEditorActionListener, View.OnClickListener {


    private val repoLists = LinkedList<Repo>()

    @Inject
    lateinit var presenter: SearchReposPresenter

    override fun showFoundRepos(data: RepoSearchResponse?) {
        repoLists.clear()
        data?.let { repoLists.addAll(data.items) }
        recyclerView.adapter.notifyDataSetChanged()
    }

    private fun startSearch(queryString: String) {
        presenter.holdFoundRepos(this, queryString)
    }

    override fun onClick(v: View?) {
        KeyBoardUtil.closeKeyboard(this)
        cancel_action_shw.postDelayed(Runnable { finish() }, 300)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_repos_activity)
        query_txt_tidt.setOnEditorActionListener(this)
        query_txt_tidt.requestFocus()
        query_txt_tidt.postDelayed(Runnable {
            KeyBoardUtil.openKeyboard(this@SearchReposActivity, query_txt_tidt)
        }, 100)

        cancel_action_shw.setOnClickListener(this)
        //recyclerView
        layRecyclerView()
    }

    private fun layRecyclerView() {
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        recyclerView.adapter = object : RcycCmmAdapter<Repo>(this, repoLists) {

            override fun addViewType() {
                addItemViewDelegate(object : ItemViewDelegate<Repo> {
                    override fun getItemViewLayoutId(): Int = R.layout.repo_item

                    override fun isForViewType(item: Repo?, position: Int): Boolean = true

                    override fun convert(holder: RcycViewHolder?, t: Repo?, position: Int) {
                        holder?.setText(R.id.repo_name_tv, t?.name)
                    }
                })
            }

            override fun convert(holder: RcycViewHolder, t: Repo, position: Int) {

            }

            override fun setListener(holder: RcycViewHolder?) {

            }

        }
    }

    override fun onPause() {
        KeyBoardUtil.closeKeyboard(this)
        super.onPause()
    }

    override fun onDestroy() {
        //保证最后存取的是最新的顺序
        KeyBoardUtil.closeKeyboard(this)
        super.onDestroy()
    }

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
        var ret = false
        if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            //发起搜索
            val skWordStr = query_txt_tidt.getText().toString()//name
            if (skWordStr.length > 0) {
                //start search action
                startSearch(skWordStr)
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
