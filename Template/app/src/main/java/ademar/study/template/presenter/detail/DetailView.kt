package ademar.study.template.presenter.detail

import ademar.study.template.model.detail.HelloWorldViewModel
import ademar.study.template.presenter.LoadDataView

interface DetailView : LoadDataView {

    fun bindHelloWorld(viewModel: HelloWorldViewModel)

}
