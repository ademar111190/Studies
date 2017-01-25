package ademar.study.template.presenter.home

import ademar.study.template.model.home.HelloWorldViewModel
import ademar.study.template.presenter.StubLoadDataView
import org.assertj.core.api.Assertions.fail

open class StubHomeView : StubLoadDataView(), HomeView {

    override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
        fail("Shouldn't call bindHelloWorld, viewModel $viewModel")
    }

}
