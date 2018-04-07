package ademar.study.test.injection

import ademar.study.test.core.model.HelloWorld
import ademar.study.test.view.base.BaseActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class LifeCycleModule(

        private val baseActivity: BaseActivity,
        private val focused: HelloWorld? = null,
        private val others: List<HelloWorld>? = null

) {

    @Provides
    @LifeCycleScope
    fun provideBaseActivity() = baseActivity

    @Provides
    @Named("focused")
    @LifeCycleScope
    fun provideFocused() = focused ?: throw IllegalStateException("You need to provide a HelloWorld object to the LifeCycleModule in order to use the focused")

    @Provides
    @Named("others")
    @LifeCycleScope
    fun provideOthers() = others ?: throw IllegalStateException("You need to provide a list of HelloWorld object to the LifeCycleModule in order to use the others")

}
