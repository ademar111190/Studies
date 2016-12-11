package ademar.study.template.injection.component

import ademar.study.template.core.injection.CoreComponent
import ademar.study.template.injection.module.ViewHolderMockModule
import ademar.study.template.injection.scope.ViewHolderScope
import dagger.Component

@ViewHolderScope
@Component(modules = arrayOf(ViewHolderMockModule::class), dependencies = arrayOf(CoreComponent::class))
interface ViewHolderMockComponent : ViewHolderComponent
