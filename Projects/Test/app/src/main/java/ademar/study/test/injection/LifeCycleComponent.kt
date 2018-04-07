package ademar.study.test.injection

import ademar.study.test.core.injection.CoreComponent
import ademar.study.test.view.common.StartActivity
import ademar.study.test.view.detail.DetailActivity
import ademar.study.test.view.detail.DetailFragment
import ademar.study.test.view.home.HomeFragment
import dagger.Component

@LifeCycleScope
@Component(modules = [LifeCycleModule::class], dependencies = [CoreComponent::class])
interface LifeCycleComponent {

    fun inject(o: StartActivity)
    fun inject(o: DetailActivity)

    fun inject(o: DetailFragment)
    fun inject(o: HomeFragment)

}
