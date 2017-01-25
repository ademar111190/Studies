package ademar.study.template.presenter

import ademar.study.template.core.model.Error
import ademar.study.template.view.base.BaseActivity
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

    override fun showError(error: Error) {
        fail("Shouldn't call showError, error: $error")
    }

    override fun showContent() {
        fail("Shouldn't call showContent")
    }

}
