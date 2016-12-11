package ademar.study.test.injection.module

import ademar.study.test.injection.scope.ActivityScope
import ademar.study.test.view.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(

        private val baseActivity: BaseActivity

) {

    @Provides
    @ActivityScope
    fun provideBaseActivity(): BaseActivity {
        return baseActivity
    }

}
