package ademar.study.template.core.injection

import ademar.study.template.core.ext.standardErrors
import ademar.study.template.core.model.StandardErrors
import ademar.study.template.core.repository.datasource.HelloWorldCloudRepository
import android.content.Context
import com.github.aurae.retrofit2.LoganSquareConverterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
class CoreModule(

        private val context: Context,
        private val apiUrl: String

) {

    @Provides
    fun provideContext(): Context {
        return context
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
    fun provideRetrofit(okHttpClient: OkHttpClient, standardErrors: StandardErrors): Retrofit {
        val retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .client(okHttpClient)
                .build()
        retrofit.standardErrors = standardErrors
        return retrofit
    }

    @Provides
    fun provideHelloWorldCloudRepository(retrofit: Retrofit): HelloWorldCloudRepository {
        return retrofit.create(HelloWorldCloudRepository::class.java)
    }

}
