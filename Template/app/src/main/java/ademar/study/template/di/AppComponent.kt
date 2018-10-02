package ademar.study.template.di

import ademar.study.template.di.modules.AppModule
import ademar.study.template.di.modules.DataSourceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            DataSourceModule::class
        ]
)
interface AppComponent
