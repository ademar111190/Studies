package ademar.study.template.test

import ademar.study.template.core.injection.CoreModule
import ademar.study.template.core.injection.DaggerCoreComponent
import ademar.study.template.injection.component.DaggerActivityMockComponent
import ademar.study.template.injection.component.DaggerFragmentMockComponent
import ademar.study.template.injection.component.DaggerViewHolderMockComponent
import ademar.study.template.injection.module.ActivityMockModule
import ademar.study.template.injection.module.FragmentMockModule
import ademar.study.template.injection.module.ViewHolderMockModule
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

    lateinit var activityMockModule: ActivityMockModule
    lateinit var fragmentMockModule: FragmentMockModule
    lateinit var viewHolderMockModule: ViewHolderMockModule

    @Mock lateinit var mockContext: Context
    @Mock lateinit var mockCoreModule: CoreModule

    private var rxError: Throwable? = null

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
        rxError = null

        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
        RxJavaPlugins.setErrorHandler { error ->
            rxError = error
        }

        activityMockModule = ActivityMockModule()
        fragmentMockModule = FragmentMockModule()
        viewHolderMockModule = ViewHolderMockModule()
        val coreComponent = DaggerCoreComponent.builder()
                .coreModule(mockCoreModule)
                .build()
        DaggerActivityMockComponent.builder()
                .activityMockModule(activityMockModule)
                .coreComponent(coreComponent)
                .build()
        DaggerFragmentMockComponent.builder()
                .fragmentMockModule(fragmentMockModule)
                .coreComponent(coreComponent)
                .build()
        DaggerViewHolderMockComponent.builder()
                .viewHolderMockModule(viewHolderMockModule)
                .coreComponent(coreComponent)
                .build()
    }

    @After
    open fun tearDown() {
        if (rxError != null) {
            fail("Error on rx: $rxError")
        }
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

}
