package ademar.study.test.model

import ademar.study.test.test.BaseTest
import org.junit.Test

class ErrorViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        ErrorViewModel(1, "Any message")
    }

}
