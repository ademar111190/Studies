package ademar.study.reddit.core.injection

import ademar.study.reddit.core.ext.standardErrors
import ademar.study.reddit.core.model.StandardErrors
import ademar.study.reddit.core.repository.datasource.CommentCloudRepository
import ademar.study.reddit.core.repository.datasource.PostCloudRepository
import android.content.Context
import com.github.aurae.retrofit2.LoganSquareConverterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
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
    fun provideCommentCloudRepository(retrofit: Retrofit): CommentCloudRepository {
        return retrofit.create(CommentCloudRepository::class.java)
    }

    @Provides
    fun providePostCloudRepository(retrofit: Retrofit): PostCloudRepository {
        return retrofit.create(PostCloudRepository::class.java)
    }

}
