package ademar.study.test.injection.module

import ademar.study.test.injection.scope.FragmentScope
import ademar.study.test.view.base.BaseActivity
import ademar.study.test.view.base.BaseFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(

        private val baseFragment: BaseFragment

) {

    @Provides
    @FragmentScope
    fun provideBaseActivity(): BaseActivity {
        return baseFragment.getBaseActivity()!!
    }

    @Provides
    @FragmentScope
    fun provideBaseFragment(): BaseFragment {
        return baseFragment
    }

}
