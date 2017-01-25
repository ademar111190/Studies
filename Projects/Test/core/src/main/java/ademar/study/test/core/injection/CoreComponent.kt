package ademar.study.test.core.injection

import ademar.study.test.core.model.StandardErrors
import ademar.study.test.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.test.core.repository.datasource.HelloWorldLocalRepository
import android.content.Context
import dagger.Component
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(CoreModule::class))
interface CoreComponent {

    val context: Context
    val standardErrors: StandardErrors
    val httpLoggingInterceptor: HttpLoggingInterceptor
    val okHttpClient: OkHttpClient
    val retrofit: Retrofit

    val helloWorldCloudRepository: HelloWorldCloudRepository
    val helloWorldLocalRepository: HelloWorldLocalRepository

}
