package ademar.study.test.presenter

import ademar.study.test.model.ErrorViewModel
import ademar.study.test.view.base.BaseActivity

interface LoadDataView {

    fun getBaseActivity(): BaseActivity?

    fun showLoading()

    fun showRetry()

    fun showContent()

    fun showError(viewModel: ErrorViewModel)

}
