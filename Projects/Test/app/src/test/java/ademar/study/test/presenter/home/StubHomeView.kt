package ademar.study.test.presenter.home

import ademar.study.test.core.model.HelloWorld
import ademar.study.test.presenter.StubLoadDataView
import junit.framework.Assert.fail

open class StubHomeView : StubLoadDataView(), HomeView {

    override fun bindHelloWorld(helloWorld: HelloWorld) {
        fail("Shouldn't call bindHelloWorld, helloWorld $helloWorld")
    }

}
