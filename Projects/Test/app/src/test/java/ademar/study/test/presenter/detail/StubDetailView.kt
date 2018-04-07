package ademar.study.test.presenter.detail

import ademar.study.test.model.DetailViewModel
import ademar.study.test.model.HelloWorldViewModel
import ademar.study.test.presenter.StubLoadDataView
import org.assertj.core.api.Assertions.fail

open class StubDetailView : StubLoadDataView(), DetailView {

    override fun bindFocus(viewModel: HelloWorldViewModel) = fail("Shouldn't call bindFocus, viewModel $viewModel")

    override fun bindDetail(viewModel: DetailViewModel) = fail("Shouldn't call bindHelloWorld, viewModel $viewModel")

}
