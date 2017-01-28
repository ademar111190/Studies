package ademar.study.reddit.model.home

import ademar.study.reddit.test.BaseTest
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class HelloWorldViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        HelloWorldViewModel("Hello World!")
    }

}
