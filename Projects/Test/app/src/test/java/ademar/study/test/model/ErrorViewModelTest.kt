package ademar.study.test.model

import ademar.study.test.test.BaseTest
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class ErrorViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        ErrorViewModel(1, "Any message")
    }

}
