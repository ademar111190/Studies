package ademar.study.test.injection.module

import ademar.study.test.injection.scope.ViewHolderScope
import ademar.study.test.view.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(

        private val baseActivity: BaseActivity

) {

    @Provides
    @ViewHolderScope
    fun provideBaseActivity(): BaseActivity {
        return baseActivity
    }

}
