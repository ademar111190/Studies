package ademar.study.test.model

import ademar.study.test.test.BaseTest
import ademar.study.test.test.Fixture.helloWorldViewModel
import org.junit.Test

class DetailViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        DetailViewModel(1, helloWorldViewModel(), listOf(helloWorldViewModel()))
    }

}
