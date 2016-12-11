package ademar.study.template.injection.module

import ademar.study.template.injection.scope.ActivityScope
import ademar.study.template.view.base.BaseActivity
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
