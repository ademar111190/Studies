package ademar.study.template.presenter.home

import ademar.study.template.model.HelloWorldViewModel
import ademar.study.template.presenter.LoadDataView

interface HomeView : LoadDataView {

    fun clearHelloWorlds()

    fun bindHelloWorld(viewModel: HelloWorldViewModel)

}
