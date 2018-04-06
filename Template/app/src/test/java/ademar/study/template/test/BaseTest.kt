package ademar.study.template.test

import ademar.study.template.R
import ademar.study.template.test.Fixture.NO_CONNECTION
import ademar.study.template.test.Fixture.UNAUTHORIZED
import ademar.study.template.test.Fixture.UNKNOWN
import android.content.Context
import android.support.annotation.CallSuper
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.fail
import org.junit.After
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseTest {

    @Mock lateinit var mockContext: Context

    private var rxError: Throwable? = null

    @Before
    @CallSuper
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
        rxError = null

        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
        RxJavaPlugins.setErrorHandler { error ->
            println("Received error $error stacktrace:")
            error.printStackTrace()
            rxError = error
        }

        whenever(mockContext.getString(R.string.error_message_unknown)).thenReturn(UNKNOWN)
        whenever(mockContext.getString(R.string.error_message_unauthorized)).thenReturn(UNAUTHORIZED)
        whenever(mockContext.getString(R.string.error_message_no_connection)).thenReturn(NO_CONNECTION)
    }

    @After
    @CallSuper
    open fun tearDown() {
        if (rxError != null) {
            fail("Error on rx: $rxError")
        }
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

}
