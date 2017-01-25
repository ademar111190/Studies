package ademar.study.template.presenter.home

import ademar.study.template.model.home.HelloWorldViewModel
import ademar.study.template.presenter.LoadDataView

interface HomeView : LoadDataView {

    fun bindHelloWorld(viewModel: HelloWorldViewModel)

}
