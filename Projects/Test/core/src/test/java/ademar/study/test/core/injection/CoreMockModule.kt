package ademar.study.test.core.injection

import ademar.study.test.core.repository.datasource.HelloWorldCloudRepository
import android.content.Context
import com.github.aurae.retrofit2.LoganSquareConverterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CoreMockModule(

        private val mockContext: Context,
        private val mockWebServer: MockWebServer

) {

    @Provides
    fun provideContext(): Context {
        return mockContext
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(mockWebServer.url(""))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun provideHelloWorldCloudRepository(retrofit: Retrofit): HelloWorldCloudRepository {
        return retrofit.create(HelloWorldCloudRepository::class.java)
    }

}
