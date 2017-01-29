package ademar.study.reddit.view.common

import ademar.study.reddit.App
import ademar.study.reddit.model.ErrorViewModel
import ademar.study.reddit.presenter.LoadDataView
import ademar.study.reddit.view.base.BaseActivity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

open class LoadViewHolder(view: View) : RecyclerView.ViewHolder(view), LoadDataView {

    protected val context: Context by lazy { itemView.context }
    protected val activity by lazy { context as BaseActivity }
    protected val app by lazy { context.applicationContext as App }

    override fun getBaseActivity(): BaseActivity? {
        return activity
    }

    override fun showLoading() {
    }

    override fun showRetry() {
    }

    override fun showError(viewModel: ErrorViewModel) {
    }

    override fun showContent() {
    }

}
