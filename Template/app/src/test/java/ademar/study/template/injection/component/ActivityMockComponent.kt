package ademar.study.template.injection.component

import ademar.study.template.core.injection.CoreComponent
import ademar.study.template.injection.module.ActivityMockModule
import ademar.study.template.injection.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(ActivityMockModule::class), dependencies = arrayOf(CoreComponent::class))
interface ActivityMockComponent : ActivityComponent
