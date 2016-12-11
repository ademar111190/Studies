package ademar.study.template.presenter

import ademar.study.template.core.model.Error
import ademar.study.template.view.base.BaseActivity

interface LoadDataView {

    fun getBaseActivity(): BaseActivity?

    fun showLoading()

    fun showRetry()

    fun showContent()

    fun showError(error: Error)

}
