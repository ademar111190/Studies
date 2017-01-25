package ademar.study.test.presenter.home

import ademar.study.test.model.home.HelloWorldViewModel
import ademar.study.test.presenter.LoadDataView

interface HomeView : LoadDataView {

    fun bindHelloWorld(viewModel: HelloWorldViewModel)

}
