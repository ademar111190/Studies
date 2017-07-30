package ademar.study.test.core.test

import ademar.study.test.core.R
import ademar.study.test.core.injection.CoreMockModule
import ademar.study.test.core.injection.DaggerCoreMockComponent
import ademar.study.test.core.model.Error
import ademar.study.test.core.model.StandardErrors
import android.content.Context
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var coreMockModule: CoreMockModule
    lateinit var mockErrorUnknown: Error
    lateinit var mockErrorUnauthorized: Error
    lateinit var mockErrorNoConnection: Error

    @Mock lateinit var mockContext: Context
    @Mock lateinit var mockStandardErrors: StandardErrors

    private var rxError: Throwable? = null

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)

        RxAndroidPlugins.setMainThreadSchedulerHandler {
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

        mockWebServer = MockWebServer()
        coreMockModule = CoreMockModule(mockContext, mockWebServer)
        DaggerCoreMockComponent.builder()
                .coreMockModule(coreMockModule)
                .build()

        mockErrorUnknown = Error().apply {
            code = 1
            message = "Mock Error Unknown"
        }
        mockErrorUnauthorized = Error().apply {
            code = 2
            message = "Mock Error Unauthorized"
        }
        mockErrorNoConnection = Error().apply {
            code = 3
            message = "Mock Error No Connection"
        }

        whenever(mockContext.getString(R.string.error_message_unknown)).thenReturn("UNKNOWN")
        whenever(mockContext.getString(R.string.error_message_unauthorized)).thenReturn("UNAUTHORIZED")
        whenever(mockContext.getString(R.string.error_message_no_connection)).thenReturn("NO_CONNECTION")

        whenever(mockStandardErrors.UNKNOWN).thenReturn(mockErrorUnknown)
        whenever(mockStandardErrors.UNAUTHORIZED).thenReturn(mockErrorUnauthorized)
        whenever(mockStandardErrors.NO_CONNECTION).thenReturn(mockErrorNoConnection)
    }

    @After
    open fun tearDown() {
        if (rxError != null) {
            Assertions.fail("Error on rx: $rxError")
        }
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

}
