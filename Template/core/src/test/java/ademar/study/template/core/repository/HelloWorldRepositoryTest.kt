package ademar.study.template.core.repository

import ademar.study.template.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.template.core.repository.datasource.HelloWorldMemoryRepository
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

    @Mock lateinit var mockHelloWorldMemoryRepository: HelloWorldMemoryRepository

    override fun setUp() {
        super.setUp()
        nextCalls = 0
        errorCalled = false
        successCalled = false
        mockWebServer.start()
        mockContext = coreMockModule.provideContext()
        val mockHttpLoggingInterceptor = coreMockModule.provideHttpLoggingInterceptor()
        val mockOkHttpClient = coreMockModule.provideOkHttpClient(mockHttpLoggingInterceptor)
        mockRetrofit = coreMockModule.provideRetrofit(mockOkHttpClient)
        mockHelloWorldCloudRepository = coreMockModule.provideHelloWorldCloudRepository(mockRetrofit)
    }

    override fun tearDown() {
        super.tearDown()
        mockWebServer.shutdown()
    }

    @Test
    fun testHelloWorld_successService() {
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(readJson("helloWorld"))
        mockWebServer.enqueue(mockResponse)

        val repository = HelloWorldRepository(mockHelloWorldCloudRepository, mockHelloWorldMemoryRepository)

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
    fun testHelloWorld_successCached_noUpdate() {
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(readJson("helloWorld"))
        mockWebServer.enqueue(mockResponse)

        val mockHelloWorld = Fixture.helloWorld()
        whenever(mockHelloWorldMemoryRepository.helloWorld).thenReturn(mockHelloWorld)

        val repository = HelloWorldRepository(mockHelloWorldCloudRepository, mockHelloWorldMemoryRepository)

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
    fun testHelloWorld_successCached_update() {
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(readJson("helloWorldUpdate"))
        mockWebServer.enqueue(mockResponse)

        whenever(mockHelloWorldMemoryRepository.helloWorld).thenReturn(Fixture.helloWorld())

        val repository = HelloWorldRepository(mockHelloWorldCloudRepository, mockHelloWorldMemoryRepository)

        repository.getHelloWorld()
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
    fun testHelloWorld_successCached_error() {
        val mockResponse = MockResponse().setResponseCode(0)
        mockWebServer.enqueue(mockResponse)

        val mockHelloWorld = Fixture.helloWorld()
        whenever(mockHelloWorldMemoryRepository.helloWorld).thenReturn(mockHelloWorld)

        val repository = HelloWorldRepository(mockHelloWorldCloudRepository, mockHelloWorldMemoryRepository)

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
        mockWebServer.enqueue(mockResponse)

        val repository = HelloWorldRepository(mockHelloWorldCloudRepository, mockHelloWorldMemoryRepository)

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
