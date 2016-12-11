package ademar.study.template.injection.component

import ademar.study.template.core.injection.CoreComponent
import ademar.study.template.injection.module.ViewHolderModule
import ademar.study.template.injection.scope.ViewHolderScope
import dagger.Component

@ViewHolderScope
@Component(modules = arrayOf(ViewHolderModule::class), dependencies = arrayOf(CoreComponent::class))
interface ViewHolderComponent
