package ademar.study.template.model

import ademar.study.template.test.BaseTest
import org.junit.Test

class HelloWorldViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        HelloWorldViewModel("Hello World!")
    }

}
