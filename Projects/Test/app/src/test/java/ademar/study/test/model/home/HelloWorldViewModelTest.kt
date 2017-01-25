package ademar.study.test.model.home

import ademar.study.test.test.BaseTest
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class HelloWorldViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        HelloWorldViewModel("Hello World!")
    }

}
