package ademar.study.template.core.injection

import ademar.study.template.core.repository.datasource.HelloWorldCloudRepository
import android.content.Context
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class CoreMockModule(

        private val mockContext: Context,
        private val mockWebServer: MockWebServer

) {

    @Provides
    fun provideContext() = mockContext

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .add(ApplicationJsonAdapterFactory.INSTANCE)
                    .build()))
            .client(okHttpClient)
            .build()

    @Provides
    fun provideHelloWorldCloudRepository(retrofit: Retrofit): HelloWorldCloudRepository =
            retrofit.create(HelloWorldCloudRepository::class.java)

}
