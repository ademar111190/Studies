package ademar.study.test.model

import ademar.study.test.test.BaseTest
import org.junit.Test

class HelloWorldViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        HelloWorldViewModel("Hello World!")
    }

}
