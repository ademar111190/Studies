package ademar.study.test.presenter.home

import ademar.study.test.model.HelloWorldViewModel
import ademar.study.test.presenter.StubLoadDataView
import org.assertj.core.api.Assertions.fail

open class StubHomeView : StubLoadDataView(), HomeView {

    override fun clearHelloWorlds() = fail("Shouldn't call clearHelloWorlds")

    override fun bindHelloWorld(viewModel: HelloWorldViewModel) = fail("Shouldn't call bindHelloWorld, viewModel $viewModel")

}
