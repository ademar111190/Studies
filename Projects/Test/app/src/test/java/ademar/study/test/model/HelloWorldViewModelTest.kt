package ademar.study.test.model

import ademar.study.test.test.BaseTest
import ademar.study.test.test.Fixture.IMAGE
import ademar.study.test.test.Fixture.MESSAGE
import org.junit.Test

class HelloWorldViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        HelloWorldViewModel(MESSAGE, IMAGE)
    }

}
