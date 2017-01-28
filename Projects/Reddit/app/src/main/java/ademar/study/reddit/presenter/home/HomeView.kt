package ademar.study.reddit.presenter.home

import ademar.study.reddit.model.home.HelloWorldViewModel
import ademar.study.reddit.presenter.LoadDataView

interface HomeView : LoadDataView {

    fun bindHelloWorld(viewModel: HelloWorldViewModel)

}
