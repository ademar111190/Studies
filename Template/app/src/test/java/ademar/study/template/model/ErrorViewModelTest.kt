package ademar.study.template.model

import ademar.study.template.test.BaseTest
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class ErrorViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        ErrorViewModel(1, "Any message")
    }

}
