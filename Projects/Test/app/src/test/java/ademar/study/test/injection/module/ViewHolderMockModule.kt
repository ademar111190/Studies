package ademar.study.test.injection.module

import ademar.study.test.injection.scope.ViewHolderScope
import ademar.study.test.view.base.BaseActivity
import dagger.Module
import dagger.Provides
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@Module
class ViewHolderMockModule {

    @Mock lateinit var mockBaseActivity: BaseActivity

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Provides
    @ViewHolderScope
    fun provideBaseActivity(): BaseActivity {
        return mockBaseActivity
    }

}
