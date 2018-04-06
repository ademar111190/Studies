package ademar.study.template.injection

import ademar.study.template.core.injection.CoreComponent
import dagger.Component

@LifeCycleScope
@Component(modules = [(LifeCycleMockModule::class)], dependencies = [(CoreComponent::class)])
interface LifeCycleMockComponent : LifeCycleComponent
