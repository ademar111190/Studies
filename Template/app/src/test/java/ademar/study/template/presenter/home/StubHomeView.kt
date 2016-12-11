package ademar.study.template.presenter.home

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.presenter.StubLoadDataView
import junit.framework.Assert.fail

open class StubHomeView : StubLoadDataView(), HomeView {

    override fun bindHelloWorld(helloWorld: HelloWorld) {
        fail("Shouldn't call bindHelloWorld, helloWorld $helloWorld")
    }

}
