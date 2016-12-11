package ademar.study.test.injection.component

import ademar.study.test.core.injection.CoreComponent
import ademar.study.test.injection.module.FragmentMockModule
import ademar.study.test.injection.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = arrayOf(FragmentMockModule::class), dependencies = arrayOf(CoreComponent::class))
interface FragmentMockComponent : FragmentComponent
