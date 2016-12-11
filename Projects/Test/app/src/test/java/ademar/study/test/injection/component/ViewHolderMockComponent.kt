package ademar.study.test.injection.component

import ademar.study.test.core.injection.CoreComponent
import ademar.study.test.injection.module.ViewHolderMockModule
import ademar.study.test.injection.scope.ViewHolderScope
import dagger.Component

@ViewHolderScope
@Component(modules = arrayOf(ViewHolderMockModule::class), dependencies = arrayOf(CoreComponent::class))
interface ViewHolderMockComponent : ViewHolderComponent
