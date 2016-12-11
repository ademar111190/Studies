package ademar.study.test

import ademar.study.test.core.injection.CoreComponent
import ademar.study.test.core.injection.CoreModule
import ademar.study.test.core.injection.DaggerCoreComponent
import android.app.Application

class App : Application() {

    val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
                .coreModule(CoreModule(this, getString(R.string.api_url)))
                .build()
    }

}
