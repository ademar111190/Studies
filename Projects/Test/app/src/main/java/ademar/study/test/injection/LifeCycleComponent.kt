package ademar.study.test.injection

import ademar.study.test.core.injection.CoreComponent
import ademar.study.test.view.common.StartActivity
import ademar.study.test.view.detail.DetailFragment
import ademar.study.test.view.home.HomeFragment
import dagger.Component

@LifeCycleScope
@Component(modules = arrayOf(LifeCycleModule::class), dependencies = arrayOf(CoreComponent::class))
interface LifeCycleComponent {

    fun inject(o: StartActivity)

    fun inject(o: DetailFragment)
    fun inject(o: HomeFragment)

}
