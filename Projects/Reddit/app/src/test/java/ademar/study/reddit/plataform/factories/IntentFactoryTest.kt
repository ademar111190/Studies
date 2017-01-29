package ademar.study.reddit.plataform.factories

import ademar.study.reddit.test.BaseTest
import org.junit.Test

class IntentFactoryTest : BaseTest() {

    @Test
    fun testMakeIntent() {
        val intentFactory = IntentFactory()
        intentFactory.makeIntent()
    }

}
