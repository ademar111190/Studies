package ademar.study.test.navigation

import ademar.study.test.R
import ademar.study.test.test.BaseTest
import ademar.study.test.view.base.BaseActivity
import ademar.study.test.view.detail.DetailActivity
import ademar.study.test.view.detail.DetailFragment
import ademar.study.test.view.home.HomeActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

class FlowControllerTest : BaseTest() {

    @Mock lateinit var mockBaseActivity: BaseActivity
    @Mock lateinit var mockResources: Resources
    @Mock lateinit var mockFragmentManager: FragmentManager
    @Mock lateinit var mockFragmentTransaction: FragmentTransaction
    @Mock lateinit var mockIntent: Intent
    @Mock lateinit var mockIntentFactory: IntentFactory

    @SuppressLint("CommitTransaction")
    override fun setUp() {
        super.setUp()
        whenever(mockIntentFactory.makeIntent()).thenReturn(mockIntent)
        whenever(mockBaseActivity.resources).thenReturn(mockResources)
        whenever(mockBaseActivity.supportFragmentManager).thenReturn(mockFragmentManager)
        whenever(mockFragmentManager.beginTransaction()).thenReturn(mockFragmentTransaction)
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

    @Test
    fun testLaunchDetail_smallScreen() {
        whenever(mockResources.getBoolean(R.bool.large_screen)).thenReturn(false)

        val flowController = FlowController(mockBaseActivity, mockIntentFactory)

        flowController.launchDetail()

        verify(mockIntentFactory).makeIntent()
        verifyNoMoreInteractions(mockIntentFactory)
        verify(mockIntent).setClassName(mockBaseActivity, DetailActivity::class.java.name)
        verifyNoMoreInteractions(mockIntent)
        verify(mockBaseActivity).startActivity(mockIntent)
        verify(mockBaseActivity).resources
        verifyNoMoreInteractions(mockBaseActivity)
    }

    @Test
    fun testLaunchDetail_largeScreen() {
        whenever(mockResources.getBoolean(R.bool.large_screen)).thenReturn(true)
        whenever(mockFragmentTransaction.replace(eq(R.id.detail_fragment), any(DetailFragment::class.java))).thenReturn(mockFragmentTransaction)

        val flowController = FlowController(mockBaseActivity, mockIntentFactory)

        flowController.launchDetail()

        verify(mockFragmentTransaction).replace(eq(R.id.detail_fragment), any(DetailFragment::class.java))
        verify(mockFragmentTransaction).commit()
        verifyNoMoreInteractions(mockFragmentTransaction)
    }

}
