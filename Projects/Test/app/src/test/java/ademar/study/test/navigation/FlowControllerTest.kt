package ademar.study.test.navigation

import ademar.study.test.test.BaseTest
import ademar.study.test.view.base.BaseActivity
import ademar.study.test.view.home.HomeActivity
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
