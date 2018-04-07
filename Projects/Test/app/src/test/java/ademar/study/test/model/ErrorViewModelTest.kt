package ademar.study.test.model

import ademar.study.test.test.BaseTest
import ademar.study.test.test.Fixture.MESSAGE
import org.junit.Test

class ErrorViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        ErrorViewModel(MESSAGE)
    }

}
