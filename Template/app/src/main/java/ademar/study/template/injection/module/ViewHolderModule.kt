package ademar.study.template.injection.module

import ademar.study.template.injection.scope.ViewHolderScope
import ademar.study.template.view.base.BaseActivity
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
