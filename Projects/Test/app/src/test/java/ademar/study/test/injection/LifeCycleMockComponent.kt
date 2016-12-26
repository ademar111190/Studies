package ademar.study.test.injection

import ademar.study.test.core.injection.CoreComponent
import dagger.Component

@LifeCycleScope
@Component(modules = arrayOf(LifeCycleMockModule::class), dependencies = arrayOf(CoreComponent::class))
interface LifeCycleMockComponent : LifeCycleComponent
