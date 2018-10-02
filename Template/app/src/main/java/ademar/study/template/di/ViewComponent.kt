package ademar.study.template.di

import ademar.study.template.di.modules.ViewModule
import dagger.Component

@ViewScope
@Component(
        modules = [ViewModule::class],
        dependencies = [AppComponent::class]
)
interface ViewComponent
