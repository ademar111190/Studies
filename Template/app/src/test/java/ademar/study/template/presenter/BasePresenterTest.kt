package ademar.study.template.presenter

import ademar.study.template.test.BaseTest
import org.junit.Test

class BasePresenterTest : BaseTest() {

    @Test
    fun testOnAttachView() {
        val stubView = object : StubLoadDataView() {}
        val presenter = BasePresenter<StubLoadDataView>()
        presenter.onAttachView(stubView)
    }

    @Test
    fun testOnDetachView() {
        val presenter = BasePresenter<StubLoadDataView>()
        presenter.onDetachView()
    }

}
