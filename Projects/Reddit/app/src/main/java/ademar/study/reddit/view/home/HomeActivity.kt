package ademar.study.reddit.view.home

import ademar.study.reddit.R
import ademar.study.reddit.plataform.ext.inflate
import ademar.study.reddit.injection.LifeCycleModule
import ademar.study.reddit.model.ErrorViewModel
import ademar.study.reddit.model.home.PostViewModel
import ademar.study.reddit.presenter.home.HomePresenter
import ademar.study.reddit.presenter.home.HomeView
import ademar.study.reddit.view.base.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.github.ybq.endless.Endless
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.load_item.view.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

    @Inject lateinit var presenter: HomePresenter
    @Inject lateinit var adapter: PostAdapter

    private lateinit var endless: Endless
    private var loadView: View? = null

    override fun makeLifeCycleModule(): LifeCycleModule {
        return LifeCycleModule(getBaseActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        prepareTaskDescription()

        component.inject(this)
        presenter.onAttachView(this)

        toolbar.inflateMenu(R.menu.home)
        toolbar.setOnMenuItemClickListener { menu ->
            val isReload = menu.itemId == R.id.refresh
            if (isReload) {
                presenter.onReloadClick()
            }
            isReload
        }

        reload.setOnClickListener {
            presenter.onReloadClick()
        }

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        loadView = list.inflate(R.layout.load_item)
        loadView?.retry?.setOnClickListener {
            presenter.onPreviousPageClick()
        }

        endless = Endless.applyTo(list, loadView)
        endless.setLoadMoreListener {
            presenter.onPreviousPageClick()
        }

        presenter.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun showLoading() {
        list.visibility = GONE
        load.visibility = VISIBLE
        reload.visibility = GONE
    }

    override fun showRetry() {
        list.visibility = GONE
        load.visibility = GONE
        reload.visibility = VISIBLE
    }

    override fun showContent() {
        list.visibility = VISIBLE
        load.visibility = GONE
        reload.visibility = GONE
    }

    override fun clearPosts() {
        adapter.posts.clear()
        adapter.notifyDataSetChanged()
    }

    override fun bindPost(viewModel: PostViewModel) {
        endless.loadMoreComplete()
        adapter.posts.add(viewModel)
        adapter.notifyItemInserted(adapter.posts.size - 1)
    }

    override fun showUnloadedPosts() {
        endless.isLoadMoreAvailable = true
    }

    override fun hideUnloadedPosts() {
        endless.isLoadMoreAvailable = false
    }

    override fun showUnloadedError(viewModel: ErrorViewModel) {
        loadView?.let { loadView ->
            loadView.text.text = viewModel.message
            loadView.load.visibility = GONE
            loadView.text.visibility = VISIBLE
            loadView.retry.visibility = VISIBLE
        }
    }

    override fun hideUnloadedError() {
        loadView?.let { loadView ->
            loadView.load.visibility = VISIBLE
            loadView.text.visibility = GONE
            loadView.retry.visibility = GONE
        }
    }

    companion object {

        fun populateIntent(intent: Intent, context: Context): Intent {
            intent.setClassName(context, HomeActivity::class.java.name)
            return intent
        }

    }

}
