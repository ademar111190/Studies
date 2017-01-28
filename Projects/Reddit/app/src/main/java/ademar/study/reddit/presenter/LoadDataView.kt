package ademar.study.reddit.presenter

import ademar.study.reddit.model.ErrorViewModel
import ademar.study.reddit.view.base.BaseActivity

interface LoadDataView {

    fun getBaseActivity(): BaseActivity?

    fun showLoading()

    fun showRetry()

    fun showContent()

    fun showError(viewModel: ErrorViewModel)

}
