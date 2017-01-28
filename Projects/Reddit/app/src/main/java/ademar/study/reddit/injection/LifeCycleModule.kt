package ademar.study.reddit.injection

import ademar.study.reddit.view.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class LifeCycleModule(

        private val baseActivity: BaseActivity

) {

    @Provides
    @LifeCycleScope
    fun provideBaseActivity(): BaseActivity {
        return baseActivity
    }

}
