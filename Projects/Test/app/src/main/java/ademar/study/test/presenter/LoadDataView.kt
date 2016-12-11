package ademar.study.test.presenter

import ademar.study.test.core.model.Error
import ademar.study.test.view.base.BaseActivity

interface LoadDataView {

    fun getBaseActivity(): BaseActivity?

    fun showLoading()

    fun showRetry()

    fun showContent()

    fun showError(error: Error)

}
