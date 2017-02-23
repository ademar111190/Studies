package ademar.study.template.model

import ademar.study.template.model.HelloWorldViewModel
import ademar.study.template.test.BaseTest
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class HelloWorldViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        HelloWorldViewModel("Hello World!")
    }

}
