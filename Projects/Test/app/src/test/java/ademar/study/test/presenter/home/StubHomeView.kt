package ademar.study.test.presenter.home

import ademar.study.test.model.home.HelloWorldViewModel
import ademar.study.test.presenter.StubLoadDataView
import org.assertj.core.api.Assertions.fail

open class StubHomeView : StubLoadDataView(), HomeView {

    override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
        fail("Shouldn't call bindHelloWorld, viewModel $viewModel")
    }

}
