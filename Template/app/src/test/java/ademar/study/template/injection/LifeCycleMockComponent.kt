package ademar.study.template.injection

import ademar.study.template.core.injection.CoreComponent
import dagger.Component

@LifeCycleScope
@Component(modules = arrayOf(LifeCycleMockModule::class), dependencies = arrayOf(CoreComponent::class))
interface LifeCycleMockComponent : LifeCycleComponent
