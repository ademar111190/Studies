package ademar.study.test.core.test

import ademar.study.test.core.injection.CoreMockModule
import ademar.study.test.core.injection.DaggerCoreMockComponent
import android.content.Context
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var coreMockModule: CoreMockModule

    @Mock lateinit var mockContext: Context

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)

        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
        RxAndroidPlugins.setMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }

        mockWebServer = MockWebServer()
        coreMockModule = CoreMockModule(mockContext, mockWebServer)
        DaggerCoreMockComponent.builder()
                .coreMockModule(coreMockModule)
                .build()
    }

    @After
    open fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

}
