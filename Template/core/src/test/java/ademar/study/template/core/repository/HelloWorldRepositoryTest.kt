package ademar.study.template.core.repository

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.template.core.repository.datasource.HelloWorldLocalRepository
import ademar.study.template.core.test.BaseTest
import ademar.study.template.core.test.Fixture
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import retrofit2.Retrofit
import org.mockito.Mockito.`when` as whenever

class HelloWorldRepositoryTest : BaseTest() {

    private lateinit var mockRetrofit: Retrofit
    private lateinit var mockHelloWorldCloudRepository: HelloWorldCloudRepository

    @Mock lateinit var mockHelloWorldLocalRepository: HelloWorldLocalRepository
    @Mock lateinit var mockOnNext: (HelloWorld) -> Unit
    @Mock lateinit var mockOnError: (Throwable) -> Unit
    @Mock lateinit var mockOnSuccess: () -> Unit

    override fun setUp() {
        super.setUp()
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
        var called = false
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(Fixture.helloWorld.JSON)
        mockWebServer.enqueue(mockResponse)

        val repository = HelloWorldRepository(mockRetrofit, mockHelloWorldCloudRepository, mockHelloWorldLocalRepository)

        repository.getHelloWorld()
                .subscribe({ helloWorld ->
                    assertThat(helloWorld).isNotNull()
                    called = true
                }, mockOnError, mockOnSuccess)

        verifyZeroInteractions(mockOnError)
        verify(mockOnSuccess).invoke()
        verifyNoMoreInteractions(mockOnSuccess)
        assertThat(called).isTrue()
    }

    @Test
    fun testHelloWorld_successCached() {
        var called = false
        val mockHelloWorld = Fixture.helloWorld.makeModel()

        whenever(mockHelloWorldLocalRepository.helloWorld).thenReturn(mockHelloWorld)

        val repository = HelloWorldRepository(mockRetrofit, mockHelloWorldCloudRepository, mockHelloWorldLocalRepository)

        repository.getHelloWorld()
                .subscribe({ helloWorld ->
                    assertThat(helloWorld).isEqualTo(mockHelloWorld)
                    called = true
                }, mockOnError, mockOnSuccess)

        verifyZeroInteractions(mockOnError)
        verify(mockOnSuccess).invoke()
        verifyNoMoreInteractions(mockOnSuccess)
        assertThat(called).isTrue()
    }

    @Test
    fun testHelloWorld_successError() {
        var called = false
        val mockResponse = MockResponse().setResponseCode(0)
                .setBody(Fixture.error.JSON)
        mockWebServer.enqueue(mockResponse)

        val repository = HelloWorldRepository(mockRetrofit, mockHelloWorldCloudRepository, mockHelloWorldLocalRepository)

        repository.getHelloWorld()
                .subscribe(mockOnNext, { error ->
                    assertThat(error).isNotNull()
                    called = true
                }, mockOnSuccess)

        verifyZeroInteractions(mockOnNext)
        verifyZeroInteractions(mockOnSuccess)
        assertThat(called).isTrue()
    }

}
