package ademar.study.reddit.model

import ademar.study.reddit.test.BaseTest
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class ErrorViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        ErrorViewModel(1, "Any message")
    }

}
