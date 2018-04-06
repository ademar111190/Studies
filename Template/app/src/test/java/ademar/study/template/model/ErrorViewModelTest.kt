package ademar.study.template.model

import ademar.study.template.test.BaseTest
import ademar.study.template.test.Fixture.MESSAGE
import org.junit.Test

class ErrorViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        ErrorViewModel(MESSAGE)
    }

}
