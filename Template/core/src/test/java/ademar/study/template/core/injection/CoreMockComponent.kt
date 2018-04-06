package ademar.study.template.core.injection

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(CoreMockModule::class)])
interface CoreMockComponent : CoreComponent
