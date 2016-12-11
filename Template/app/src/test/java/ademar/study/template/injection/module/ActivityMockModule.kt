package ademar.study.template.injection.module

import ademar.study.template.injection.scope.ActivityScope
import ademar.study.template.view.base.BaseActivity
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
