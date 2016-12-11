package ademar.study.test.injection.component

import ademar.study.test.core.injection.CoreComponent
import ademar.study.test.injection.module.ViewHolderModule
import ademar.study.test.injection.scope.ViewHolderScope
import dagger.Component

@ViewHolderScope
@Component(modules = arrayOf(ViewHolderModule::class), dependencies = arrayOf(CoreComponent::class))
interface ViewHolderComponent
