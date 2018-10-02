package ademar.study.template

import ademar.study.template.BuildConfig.DEBUG
import ademar.study.template.di.AppComponent
import ademar.study.template.di.DaggerAppComponent
import ademar.study.template.di.modules.AppModule
import ademar.study.template.di.modules.DataSourceModule
import android.app.Application
import timber.log.Timber.*

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dataSourceModule(DataSourceModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        setupLog()
    }

    private fun setupLog() = plant(if (DEBUG) DebugTree() else object : Tree() {
        override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
            // log to bug track
            throwable?.printStackTrace()
        }
    })

}
