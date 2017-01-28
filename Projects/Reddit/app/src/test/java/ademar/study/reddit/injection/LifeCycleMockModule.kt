package ademar.study.reddit.injection

import ademar.study.reddit.view.base.BaseActivity
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
