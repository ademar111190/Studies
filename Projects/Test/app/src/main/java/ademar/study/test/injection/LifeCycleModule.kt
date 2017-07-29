package ademar.study.test.injection

import ademar.study.test.view.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class LifeCycleModule(

        private val baseActivity: BaseActivity

) {

    @Provides
    @LifeCycleScope
    fun provideBaseActivity() = baseActivity

}
