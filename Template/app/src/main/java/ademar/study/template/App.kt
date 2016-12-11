package ademar.study.template

import ademar.study.template.core.injection.CoreComponent
import ademar.study.template.core.injection.CoreModule
import ademar.study.template.core.injection.DaggerCoreComponent
import android.app.Application

class App : Application() {

    val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
                .coreModule(CoreModule(this, getString(R.string.api_url)))
                .build()
    }

}
