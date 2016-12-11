package ademar.study.template.injection.component

import ademar.study.template.core.injection.CoreComponent
import ademar.study.template.injection.module.ActivityModule
import ademar.study.template.injection.scope.ActivityScope
import ademar.study.template.view.common.StartActivity
import ademar.study.template.view.home.HomeActivity
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(CoreComponent::class))
interface ActivityComponent {

    fun inject(o: StartActivity)
    fun inject(o: HomeActivity)

}
