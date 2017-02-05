package ademar.study.reddit.view.comment

import ademar.study.reddit.R
import ademar.study.reddit.injection.LifeCycleModule
import ademar.study.reddit.model.comment.CommentViewModel
import ademar.study.reddit.presenter.comment.CommentPresenter
import ademar.study.reddit.presenter.comment.CommentView
import ademar.study.reddit.view.base.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.comment_activity.*
import javax.inject.Inject

class CommentActivity : BaseActivity(), CommentView {

    @Inject lateinit var presenter: CommentPresenter
    @Inject lateinit var adapter: CommentAdapter

    override fun makeLifeCycleModule(): LifeCycleModule {
        return LifeCycleModule(getBaseActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comment_activity)
        prepareTaskDescription()

        component.inject(this)
        presenter.onAttachView(this)

        toolbar.setNavigationOnClickListener { finish() }

        reload.setOnClickListener {
            presenter.onReloadClick()
        }

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        presenter.onStart(intent.getStringExtra(PARAM_LINK))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun showLoading() {
        list.visibility = GONE
        load.visibility = VISIBLE
        reload.visibility = GONE
        empty.visibility = GONE
    }

    override fun showRetry() {
        list.visibility = GONE
        load.visibility = GONE
        reload.visibility = VISIBLE
        empty.visibility = GONE
    }

    override fun showContent() {
        list.visibility = VISIBLE
        load.visibility = GONE
        reload.visibility = GONE
        empty.visibility = GONE
    }

    override fun showEmpty() {
        list.visibility = GONE
        load.visibility = GONE
        reload.visibility = GONE
        empty.visibility = VISIBLE
    }

    override fun clearComments() {
        adapter.comments.clear()
        adapter.notifyDataSetChanged()
    }

    override fun bindComment(viewModel: CommentViewModel) {
        adapter.comments.add(viewModel)
        adapter.notifyItemInserted(adapter.comments.size - 1)
    }

    companion object {

        private val PARAM_LINK = "PARAM_LINK"

        fun populateIntent(intent: Intent, context: Context, link: String): Intent {
            intent.setClassName(context, CommentActivity::class.java.name)
            intent.putExtra(PARAM_LINK, link)
            return intent
        }

    }

}
