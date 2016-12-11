package ademar.study.template.injection.module

import ademar.study.template.injection.scope.FragmentScope
import ademar.study.template.view.base.BaseActivity
import ademar.study.template.view.base.BaseFragment
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
