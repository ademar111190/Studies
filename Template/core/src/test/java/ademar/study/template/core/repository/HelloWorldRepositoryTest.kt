package ademar.study.template.core.repository

import ademar.study.template.core.injection.ApplicationJsonAdapterFactory
import ademar.study.template.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.template.core.repository.datasource.HelloWorldMemoryRepository
import ademar.study.template.core.test.BaseTest
import ademar.study.template.core.test.Fixture
import com.nhaarman.mockito_kotlin.whenever
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class HelloWorldRepositoryTest : BaseTest() {

    private lateinit var mockRetrofit: Retrofit
    private lateinit var mockHelloWorldCloudRepository: HelloWorldCloudRepository

    private var nextCalls = 0
    private var errorCalled = false
    private var successCalled = false

    @Mock lateinit var mockHelloWorldMemoryRepository: HelloWorldMemoryRepository

    override fun setUp() {
        super.setUp()
        nextCalls = 0
        errorCalled = false
        successCalled = false
        mockWebServer.start()

        mockRetrofit = Retrofit.Builder()
                .baseUrl(mockWebServer.url(""))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .add(ApplicationJsonAdapterFactory.INSTANCE)
                        .build()))
                .client(OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build())
                .build()

        mockHelloWorldCloudRepository = mockRetrofit.create(HelloWorldCloudRepository::class.java)
    }

    override fun tearDown() {
        super.tearDown()
        mockWebServer.shutdown()
    }

    @Test
    fun testHellos_successService() {
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(readJson("helloWorlds"))
        mockWebServer.enqueue(mockResponse)

        whenever(mockHelloWorldMemoryRepository.hellos).thenReturn(null)

        val repository = HelloWorldRepository(mockHelloWorldCloudRepository, mockHelloWorldMemoryRepository)

        repository.getAllHelloWorld()
                .subscribe({
                    nextCalls++
                }, {
                    errorCalled = true
                }, {
                    successCalled = true
                })

        assertThat(nextCalls).isEqualTo(1)
        assertThat(errorCalled).isFalse()
        assertThat(successCalled).isTrue()
    }

    @Test
    fun testHellos_successCached_noUpdate() {
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(readJson("helloWorlds"))
        mockWebServer.enqueue(mockResponse)

        whenever(mockHelloWorldMemoryRepository.hellos).thenReturn(listOf(Fixture.helloWorld()))

        val repository = HelloWorldRepository(mockHelloWorldCloudRepository, mockHelloWorldMemoryRepository)

        repository.getAllHelloWorld()
                .subscribe({
                    nextCalls++
                }, {
                    errorCalled = true
                }, {
                    successCalled = true
                })

        assertThat(nextCalls).isEqualTo(1)
        assertThat(errorCalled).isFalse()
        assertThat(successCalled).isTrue()
    }

    @Test
    fun testHellos_successCached_update() {
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(readJson("helloWorldsUpdate"))
        mockWebServer.enqueue(mockResponse)

        whenever(mockHelloWorldMemoryRepository.hellos).thenReturn(listOf(Fixture.helloWorld()))

        val repository = HelloWorldRepository(mockHelloWorldCloudRepository, mockHelloWorldMemoryRepository)

        repository.getAllHelloWorld()
                .subscribe({
                    nextCalls++
                }, {
                    errorCalled = true
                }, {
                    successCalled = true
                })

        assertThat(nextCalls).isEqualTo(2)
        assertThat(errorCalled).isFalse()
        assertThat(successCalled).isTrue()
    }

    @Test
    fun testHellos_successCached_error() {
        val mockResponse = MockResponse().setResponseCode(0)
        mockWebServer.enqueue(mockResponse)

        whenever(mockHelloWorldMemoryRepository.hellos).thenReturn(listOf(Fixture.helloWorld()))

        val repository = HelloWorldRepository(mockHelloWorldCloudRepository, mockHelloWorldMemoryRepository)

        repository.getAllHelloWorld()
                .subscribe({
                    nextCalls++
                }, {
                    errorCalled = true
                }, {
                    successCalled = true
                })

        assertThat(nextCalls).isEqualTo(1)
        assertThat(errorCalled).isFalse()
        assertThat(successCalled).isTrue()
    }

    @Test
    fun testHellos_successError() {
        val mockResponse = MockResponse().setResponseCode(0)
        mockWebServer.enqueue(mockResponse)

        whenever(mockHelloWorldMemoryRepository.hellos).thenReturn(null)

        val repository = HelloWorldRepository(mockHelloWorldCloudRepository, mockHelloWorldMemoryRepository)

        repository.getAllHelloWorld()
                .subscribe({
                    nextCalls++
                }, { error ->
                    assertThat(error).isNotNull()
                    errorCalled = true
                }, {
                    successCalled = true
                })

        assertThat(nextCalls).isEqualTo(0)
        assertThat(errorCalled).isTrue()
        assertThat(successCalled).isFalse()
    }

}
