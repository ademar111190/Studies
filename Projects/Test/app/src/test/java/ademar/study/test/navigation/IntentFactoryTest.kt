package ademar.study.test.navigation

import ademar.study.test.test.BaseTest
import org.junit.Test

class IntentFactoryTest : BaseTest() {

    @Test
    fun testMakeIntent() {
        val intentFactory = IntentFactory()
        intentFactory.makeIntent()
    }

}
