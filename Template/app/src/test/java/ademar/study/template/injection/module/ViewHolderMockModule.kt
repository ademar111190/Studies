package ademar.study.template.injection.module

import ademar.study.template.injection.scope.ViewHolderScope
import ademar.study.template.view.base.BaseActivity
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
