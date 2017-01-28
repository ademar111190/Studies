package ademar.study.reddit.presenter.home

import ademar.study.reddit.model.home.HelloWorldViewModel
import ademar.study.reddit.presenter.StubLoadDataView
import org.assertj.core.api.Assertions.fail

open class StubHomeView : StubLoadDataView(), HomeView {

    override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
        fail("Shouldn't call bindHelloWorld, viewModel $viewModel")
    }

}
