package ademar.study.reddit.injection

import ademar.study.reddit.core.injection.CoreComponent
import ademar.study.reddit.view.common.StartActivity
import ademar.study.reddit.view.home.HomeActivity
import dagger.Component

@LifeCycleScope
@Component(modules = arrayOf(LifeCycleModule::class), dependencies = arrayOf(CoreComponent::class))
interface LifeCycleComponent {

    fun inject(o: StartActivity)
    fun inject(o: HomeActivity)

}
