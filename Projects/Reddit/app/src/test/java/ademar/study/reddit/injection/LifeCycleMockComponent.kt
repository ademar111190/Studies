package ademar.study.reddit.injection

import ademar.study.reddit.core.injection.CoreComponent
import dagger.Component

@LifeCycleScope
@Component(modules = arrayOf(LifeCycleMockModule::class), dependencies = arrayOf(CoreComponent::class))
interface LifeCycleMockComponent : LifeCycleComponent
