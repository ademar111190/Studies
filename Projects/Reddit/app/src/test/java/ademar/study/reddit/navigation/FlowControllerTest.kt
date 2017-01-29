package ademar.study.reddit.navigation

import ademar.study.reddit.plataform.factories.IntentFactory
import ademar.study.reddit.test.BaseTest
import ademar.study.reddit.view.base.BaseActivity
import ademar.study.reddit.view.home.HomeActivity
import android.content.Intent
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

class FlowControllerTest : BaseTest() {

    @Mock lateinit var mockBaseActivity: BaseActivity
    @Mock lateinit var mockIntent: Intent
    @Mock lateinit var mockIntentFactory: IntentFactory

    override fun setUp() {
        super.setUp()
        whenever(mockIntentFactory.makeIntent()).thenReturn(mockIntent)
    }

    @Test
    fun testLaunchHome() {
        val flowController = FlowController(mockBaseActivity, mockIntentFactory)

        flowController.launchHome()

        verify(mockIntentFactory).makeIntent()
        verifyNoMoreInteractions(mockIntentFactory)
        verify(mockIntent).setClassName(mockBaseActivity, HomeActivity::class.java.name)
        verifyNoMoreInteractions(mockIntent)
        verify(mockBaseActivity).startActivity(mockIntent)
        verifyNoMoreInteractions(mockBaseActivity)
    }

}
