package ademar.study.test.test

import ademar.study.test.core.injection.CoreModule
import ademar.study.test.core.injection.DaggerCoreComponent
import ademar.study.test.injection.DaggerLifeCycleMockComponent
import ademar.study.test.injection.LifeCycleMockModule
import android.content.Context
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.fail
import org.junit.After
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseTest {

    lateinit var lifeCycleMockModule: LifeCycleMockModule

    @Mock lateinit var mockContext: Context
    @Mock lateinit var mockCoreModule: CoreModule

    private var rxError: Throwable? = null

    @Before
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

        lifeCycleMockModule = LifeCycleMockModule()
        val coreComponent = DaggerCoreComponent.builder()
                .coreModule(mockCoreModule)
                .build()
        DaggerLifeCycleMockComponent.builder()
                .lifeCycleMockModule(lifeCycleMockModule)
                .coreComponent(coreComponent)
                .build()
    }

    @After
    open fun tearDown() {
        if (rxError != null) {
            fail("Error on rx: $rxError")
        }
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

}
