package ademar.study.test.injection.module

import ademar.study.test.injection.scope.FragmentScope
import ademar.study.test.view.base.BaseActivity
import ademar.study.test.view.base.BaseFragment
import dagger.Module
import dagger.Provides
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@Module
class FragmentMockModule {

    @Mock lateinit var mockBaseActivity: BaseActivity
    @Mock lateinit var mockBaseFragment: BaseFragment

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Provides
    @FragmentScope
    fun provideBaseActivity(): BaseActivity {
        return mockBaseActivity
    }

    @Provides
    @FragmentScope
    fun provideBaseFragment(): BaseFragment {
        return mockBaseFragment
    }

}
