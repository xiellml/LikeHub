package com.pn.sie.likehub.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.pn.sie.likehub.Navigator
import com.pn.sie.likehub.R
import com.pn.sie.likehub.contract.IHomeMyRepos
import com.pn.sie.likehub.di.adapter.RcycCmmAdapter
import com.pn.sie.likehub.di.adapter.basic.ItemViewDelegate
import com.pn.sie.likehub.di.adapter.basic.RcycViewHolder
import com.pn.sie.likehub.xutil.LogPrinter
import com.pn.sie.likehub.xutil.toast
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*

class MainActivity : AppCompatActivity(), IHomeMyRepos.IView {

    override fun setPresenter(presenter: IHomeMyRepos.IPresenter?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMyRepos(isRefresh: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun leaveApp() {

    }

    override fun onDestroy() {
        super.onDestroy()
        leaveApp()
    }

    private val repoLists = LinkedList<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            Navigator.INSTANCE.sendRepoToEmailBox(this, "https://faked.url")
        }

        layRecyclerView()

        LogPrinter.d(stringFromJNI())
    }

    private fun layRecyclerView() {
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        my_repos_rv.layoutManager = llm
        my_repos_rv.adapter = object : RcycCmmAdapter<String>(this, repoLists) {

            override fun addViewType() {
                addItemViewDelegate(object : ItemViewDelegate<String> {
                    override fun getItemViewLayoutId(): Int = R.layout.repo_item

                    override fun isForViewType(item: String?, position: Int): Boolean = true

                    override fun convert(holder: RcycViewHolder?, t: String?, position: Int) {
                        holder?.setText(R.id.repo_name_tv, t)
                    }
                })
            }

            override fun convert(holder: RcycViewHolder, t: String, position: Int) {
            }

            override fun setListener(holder: RcycViewHolder?) {
                toast("tap me pos = " + holder?.adapterPosition)
            }
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