package ademar.study.template.model

import ademar.study.template.test.BaseTest
import org.junit.Test

class ErrorViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        ErrorViewModel("Any message")
    }

}
