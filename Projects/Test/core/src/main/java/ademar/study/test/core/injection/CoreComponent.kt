package ademar.study.test.core.injection

import ademar.study.test.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.test.core.repository.datasource.HelloWorldMemoryRepository
import android.content.Context
import dagger.Component
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {

    val context: Context
    val httpLoggingInterceptor: HttpLoggingInterceptor
    val okHttpClient: OkHttpClient
    val retrofit: Retrofit

    val helloWorldCloudRepository: HelloWorldCloudRepository
    val helloWorldMemoryRepository: HelloWorldMemoryRepository

}
