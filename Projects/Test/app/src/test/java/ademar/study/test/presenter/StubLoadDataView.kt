package ademar.study.test.presenter

import ademar.study.test.model.ErrorViewModel
import ademar.study.test.view.base.BaseActivity
import org.assertj.core.api.Assertions.fail
import org.mockito.Mockito

open class StubLoadDataView : LoadDataView {

    val mockBaseActivity: BaseActivity by lazy {
        Mockito.mock(BaseActivity::class.java)
    }

    override fun getBaseActivity(): BaseActivity {
        return mockBaseActivity
    }

    override fun showLoading() {
        fail("Shouldn't call showLoading")
    }

    override fun showRetry() {
        fail("Shouldn't call showRetry")
    }

    override fun showError(viewModel: ErrorViewModel) {
        fail("Shouldn't call showError, viewModel: $viewModel")
    }

    override fun showContent() {
        fail("Shouldn't call showContent")
    }

}
