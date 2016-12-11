package ademar.study.template.core.injection

import ademar.study.template.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.template.core.repository.datasource.HelloWorldLocalRepository
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
    val httpLoggingInterceptor: HttpLoggingInterceptor
    val okHttpClient: OkHttpClient
    val retrofit: Retrofit

    val helloWorldCloudRepository: HelloWorldCloudRepository
    val helloWorldLocalRepository: HelloWorldLocalRepository

}
