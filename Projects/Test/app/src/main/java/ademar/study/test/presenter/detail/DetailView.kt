package ademar.study.test.presenter.detail

import ademar.study.test.model.HelloWorldViewModel
import ademar.study.test.presenter.LoadDataView

interface DetailView : LoadDataView {

    fun bindHelloWorld(viewModel: HelloWorldViewModel)

}
