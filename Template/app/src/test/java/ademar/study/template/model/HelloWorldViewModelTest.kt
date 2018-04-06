package ademar.study.template.model

import ademar.study.template.test.BaseTest
import ademar.study.template.test.Fixture.IMAGE
import ademar.study.template.test.Fixture.MESSAGE
import org.junit.Test

class HelloWorldViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        HelloWorldViewModel(MESSAGE, IMAGE)
    }

}
