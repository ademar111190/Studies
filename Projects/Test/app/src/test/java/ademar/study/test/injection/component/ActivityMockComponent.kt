package ademar.study.test.injection.component

import ademar.study.test.core.injection.CoreComponent
import ademar.study.test.injection.module.ActivityMockModule
import ademar.study.test.injection.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(ActivityMockModule::class), dependencies = arrayOf(CoreComponent::class))
interface ActivityMockComponent : ActivityComponent
