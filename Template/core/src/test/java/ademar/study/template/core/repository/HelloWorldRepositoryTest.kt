package ademar.study.template.core.repository

import ademar.study.template.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.template.core.repository.datasource.HelloWorldLocalRepository
import ademar.study.template.core.test.BaseTest
import ademar.study.template.core.test.Fixture
import com.nhaarman.mockito_kotlin.whenever
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit

class HelloWorldRepositoryTest : BaseTest() {

    private lateinit var mockRetrofit: Retrofit
    private lateinit var mockHelloWorldCloudRepository: HelloWorldCloudRepository

    private var nextCalls = 0
    private var errorCalled = false
    private var successCalled = false

    @Mock lateinit var mockHelloWorldLocalRepository: HelloWorldLocalRepository

    override fun setUp() {
        super.setUp()
        nextCalls = 0
        errorCalled = false
        successCalled = false
        mockWebServer.start()
        mockContext = coreMockModule.provideContext()
        val mockHttpLoggingInterceptor = coreMockModule.provideHttpLoggingInterceptor()
        val mockOkHttpClient = coreMockModule.provideOkHttpClient(mockHttpLoggingInterceptor)
        mockRetrofit = coreMockModule.provideRetrofit(mockOkHttpClient, mockStandardErrors)
        mockHelloWorldCloudRepository = coreMockModule.provideHelloWorldCloudRepository(mockRetrofit)
    }

    override fun tearDown() {
        super.tearDown()
        mockWebServer.shutdown()
    }

    @Test
    fun testHelloWorld_successService() {
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(Fixture.helloWorld.JSON)
        mockWebServer.enqueue(mockResponse)

        val repository = HelloWorldRepository(mockRetrofit, mockHelloWorldCloudRepository, mockHelloWorldLocalRepository)

        repository.getHelloWorld()
                .subscribe({ helloWorld ->
                    assertThat(helloWorld).isNotNull()
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
    fun testHelloWorld_successCached() {
        val mockHelloWorld = Fixture.helloWorld.makeModel()

        whenever(mockHelloWorldLocalRepository.helloWorld).thenReturn(mockHelloWorld)

        val repository = HelloWorldRepository(mockRetrofit, mockHelloWorldCloudRepository, mockHelloWorldLocalRepository)

        repository.getHelloWorld()
                .subscribe({ helloWorld ->
                    assertThat(helloWorld).isEqualTo(mockHelloWorld)
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
    fun testHelloWorld_successError() {
        val mockResponse = MockResponse().setResponseCode(0)
                .setBody(Fixture.error.JSON)
        mockWebServer.enqueue(mockResponse)

        val repository = HelloWorldRepository(mockRetrofit, mockHelloWorldCloudRepository, mockHelloWorldLocalRepository)

        repository.getHelloWorld()
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

    @Test
    fun testHellos_successService() {
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(Fixture.hellos.JSON)
        mockWebServer.enqueue(mockResponse)

        whenever(mockHelloWorldLocalRepository.hellos).thenReturn(null)

        val repository = HelloWorldRepository(mockRetrofit, mockHelloWorldCloudRepository, mockHelloWorldLocalRepository)

        repository.getAllHelloWorld()
                .subscribe({ hellos ->
                    assertThat(hellos.size).isEqualTo(1)
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
    fun testHellos_successCached() {
        val mockHellos = listOf(Fixture.helloWorld.makeModel())

        whenever(mockHelloWorldLocalRepository.hellos).thenReturn(mockHellos)

        val repository = HelloWorldRepository(mockRetrofit, mockHelloWorldCloudRepository, mockHelloWorldLocalRepository)

        repository.getAllHelloWorld()
                .subscribe({ hellos ->
                    assertThat(hellos).isEqualTo(mockHellos)
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
                .setBody(Fixture.error.JSON)
        mockWebServer.enqueue(mockResponse)

        whenever(mockHelloWorldLocalRepository.hellos).thenReturn(null)

        val repository = HelloWorldRepository(mockRetrofit, mockHelloWorldCloudRepository, mockHelloWorldLocalRepository)

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
