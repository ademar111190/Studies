package ademar.study.reddit.navigation

import ademar.study.reddit.test.BaseTest
import org.junit.Test

class IntentFactoryTest : BaseTest() {

    @Test
    fun testMakeIntent() {
        val intentFactory = IntentFactory()
        intentFactory.makeIntent()
    }

}
