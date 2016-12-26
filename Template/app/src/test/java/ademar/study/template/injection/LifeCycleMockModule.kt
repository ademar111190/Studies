package ademar.study.template.injection

import ademar.study.template.view.base.BaseActivity
import dagger.Module
import dagger.Provides
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@Module
class LifeCycleMockModule {

    @Mock lateinit var mockBaseActivity: BaseActivity

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Provides
    @LifeCycleScope
    fun provideBaseActivity(): BaseActivity {
        return mockBaseActivity
    }

}
