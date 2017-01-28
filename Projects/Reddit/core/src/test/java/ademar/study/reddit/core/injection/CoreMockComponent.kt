package ademar.study.reddit.core.injection

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(CoreMockModule::class))
interface CoreMockComponent : CoreComponent
