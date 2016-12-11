package ademar.study.template.injection.module

import ademar.study.template.injection.scope.FragmentScope
import ademar.study.template.view.base.BaseActivity
import ademar.study.template.view.base.BaseFragment
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
