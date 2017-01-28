package ademar.study.reddit

import ademar.study.reddit.core.injection.CoreComponent
import ademar.study.reddit.core.injection.CoreModule
import ademar.study.reddit.core.injection.DaggerCoreComponent
import android.app.Application

class App : Application() {

    val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
                .coreModule(CoreModule(this, getString(R.string.api_url)))
                .build()
    }

}
