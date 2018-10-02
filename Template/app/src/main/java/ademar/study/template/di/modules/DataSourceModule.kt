package ademar.study.template.di.modules

import ademar.study.template.BuildConfig.*
import ademar.study.template.datasource.CurrencyCloud
import ademar.study.template.di.factory.AdapterFactory
import ademar.study.template.ext.create
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    fun provideCurrencyCloudRepository(retrofit: Retrofit) = retrofit.create<CurrencyCloud>()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
            .add(AdapterFactory.INSTANCE)
            .build()

    @Provides
    @Singleton
    fun provideHttpClient() = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, MILLISECONDS)
            .readTimeout(READ_TIMEOUT, MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, MILLISECONDS)
            .build()

}
