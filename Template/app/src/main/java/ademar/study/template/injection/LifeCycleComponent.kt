package ademar.study.template.injection

import ademar.study.template.core.injection.CoreComponent
import ademar.study.template.view.common.StartActivity
import ademar.study.template.view.detail.DetailActivity
import ademar.study.template.view.detail.DetailFragment
import ademar.study.template.view.home.HomeFragment
import dagger.Component

@LifeCycleScope
@Component(modules = [LifeCycleModule::class], dependencies = [CoreComponent::class])
interface LifeCycleComponent {

    fun inject(o: StartActivity)
    fun inject(o: DetailActivity)

    fun inject(o: DetailFragment)
    fun inject(o: HomeFragment)

}
