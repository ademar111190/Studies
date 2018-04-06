package ademar.study.template.core.test

import ademar.study.template.core.injection.CoreMockModule
import android.content.Context
import android.support.annotation.CallSuper
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

    @Mock lateinit var mockContext: Context

    private var rxError: Throwable? = null

    @Before
    @CallSuper
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
    }

    @After
    @CallSuper
    open fun tearDown() {
        if (rxError != null) {
            Assertions.fail("Error on rx: $rxError")
        }
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    fun readJson(name: String) = javaClass.classLoader
            .getResourceAsStream("json/$name.json")
            .bufferedReader()
            .use { it.readText() }

}
