package ademar.study.template.presenter.detail

import ademar.study.template.model.DetailViewModel
import ademar.study.template.model.HelloWorldViewModel
import ademar.study.template.presenter.StubLoadDataView
import org.assertj.core.api.Assertions.fail

open class StubDetailView : StubLoadDataView(), DetailView {

    override fun bindFocus(viewModel: HelloWorldViewModel) = fail("Shouldn't call bindFocus, viewModel $viewModel")

    override fun bindDetail(viewModel: DetailViewModel) = fail("Shouldn't call bindHelloWorld, viewModel $viewModel")

}
