package ademar.study.template.presenter.home

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.presenter.LoadDataView

interface HomeView : LoadDataView {

    fun bindHelloWorld(helloWorld: HelloWorld)

}
