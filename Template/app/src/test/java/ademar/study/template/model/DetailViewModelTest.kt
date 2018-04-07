package ademar.study.template.model

import ademar.study.template.test.BaseTest
import ademar.study.template.test.Fixture.helloWorldViewModel
import org.junit.Test

class DetailViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        DetailViewModel(helloWorldViewModel(), listOf(helloWorldViewModel()))
    }

}
