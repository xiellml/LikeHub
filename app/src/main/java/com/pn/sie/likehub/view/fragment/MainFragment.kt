package com.pn.sie.likehub.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pn.sie.likehub.R
import com.pn.sie.likehub.contract.IHomeMyRepos
import com.pn.sie.likehub.di.ActivityScoped
import com.pn.sie.likehub.di.adapter.RcycCmmAdapter
import com.pn.sie.likehub.di.adapter.basic.ItemViewDelegate
import com.pn.sie.likehub.di.adapter.basic.RcycViewHolder
import com.pn.sie.likehub.model.entity.Repo
import com.pn.sie.likehub.presenter.HomeMyReposPresenter
import com.pn.sie.likehub.xutil.toast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
@ActivityScoped
class MainFragment @Inject constructor() : DaggerFragment(), IHomeMyRepos.IView {


    private val repoLists = LinkedList<Repo>()

    override fun showMyRepos(isRefresh: Boolean, data: List<Repo>?) {
        repoLists.clear()
        data?.let { repoLists.addAll(it) }
        my_repos_rv.adapter.notifyDataSetChanged()
    }

    override fun leaveApp() {

    }

    override fun onDetach() {
        super.onDetach()
        leaveApp()
    }

    @Inject
    lateinit var presenter: HomeMyReposPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.start()
    }

    private fun layRecyclerView() {
        val llm = LinearLayoutManager(activity)
        llm.orientation = LinearLayoutManager.VERTICAL
        my_repos_rv.layoutManager = llm
        my_repos_rv.adapter = object : RcycCmmAdapter<Repo>(activity, repoLists) {

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
                holder?.setSubViewClickListener(holder?.itemView, RcycViewHolder.OnViewClickListener { _, adapter ->
                    toast("tap me pos = " + holder?.adapterPosition)
                })
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        layRecyclerView()
        presenter.holdMyRepos(this, false)
    }

    override fun onResume() {
        super.onResume()
        //presenter.holdMyRepos(this, true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.main_fragment, container, false)
}
