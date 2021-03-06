package ademar.study.test.presenter.home

import ademar.study.test.model.HelloWorldViewModel
import ademar.study.test.presenter.LoadDataView

interface HomeView : LoadDataView {

    fun clearHelloWorlds()

    fun bindHelloWorld(viewModel: HelloWorldViewModel)

}
