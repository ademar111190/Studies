package ademar.study.template.navigation

import ademar.study.template.test.BaseTest
import org.junit.Test

class IntentFactoryTest : BaseTest() {

    @Test
    fun testMakeIntent() {
        val intentFactory = IntentFactory()
        intentFactory.makeIntent()
    }

}
