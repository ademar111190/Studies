package ademar.study.test.injection.component

import ademar.study.test.core.injection.CoreComponent
import ademar.study.test.injection.module.ActivityModule
import ademar.study.test.injection.scope.ActivityScope
import ademar.study.test.view.common.StartActivity
import ademar.study.test.view.home.HomeActivity
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(CoreComponent::class))
interface ActivityComponent {

    fun inject(o: StartActivity)
    fun inject(o: HomeActivity)

}
