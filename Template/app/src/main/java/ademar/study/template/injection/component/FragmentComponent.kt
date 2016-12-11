package ademar.study.template.injection.component

import ademar.study.template.core.injection.CoreComponent
import ademar.study.template.injection.module.FragmentModule
import ademar.study.template.injection.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = arrayOf(FragmentModule::class), dependencies = arrayOf(CoreComponent::class))
interface FragmentComponent
