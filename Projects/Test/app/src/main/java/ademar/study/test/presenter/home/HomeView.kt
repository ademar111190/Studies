package ademar.study.test.presenter.home

import ademar.study.test.core.model.HelloWorld
import ademar.study.test.presenter.LoadDataView

interface HomeView : LoadDataView {

    fun bindHelloWorld(helloWorld: HelloWorld)

}
