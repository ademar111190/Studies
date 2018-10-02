package ademar.study.template.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
        private val context: Application
) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

}
