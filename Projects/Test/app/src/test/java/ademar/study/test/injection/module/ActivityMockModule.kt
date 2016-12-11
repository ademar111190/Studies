package ademar.study.test.injection.module

import ademar.study.test.injection.scope.ActivityScope
import ademar.study.test.view.base.BaseActivity
import dagger.Module
import dagger.Provides
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@Module
class ActivityMockModule {

    @Mock lateinit var mockBaseActivity: BaseActivity

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Provides
    @ActivityScope
    fun provideBaseActivity(): BaseActivity {
        return mockBaseActivity
    }

}
