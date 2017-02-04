package ademar.study.reddit.core.repository

import ademar.study.reddit.core.model.internal.PostResponse
import ademar.study.reddit.core.repository.datasource.PostCloudRepository
import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import retrofit2.Retrofit
import org.mockito.Mockito.`when` as whenever

class PostRepositoryTest : BaseTest() {

    @Mock lateinit var mockOnNext: (PostResponse) -> Unit
    @Mock lateinit var mockOnError: (Throwable) -> Unit
    @Mock lateinit var mockOnSuccess: () -> Unit

    private lateinit var mockRetrofit: Retrofit
    private lateinit var mockPostCloudRepository: PostCloudRepository

    override fun setUp() {
        super.setUp()
        mockWebServer.start()
        mockContext = coreMockModule.provideContext()
        val mockHttpLoggingInterceptor = coreMockModule.provideHttpLoggingInterceptor()
        val mockOkHttpClient = coreMockModule.provideOkHttpClient(mockHttpLoggingInterceptor)
        mockRetrofit = coreMockModule.provideRetrofit(mockOkHttpClient, mockStandardErrors)
        mockPostCloudRepository = coreMockModule.providePostCloudRepository(mockRetrofit)
    }

    override fun tearDown() {
        super.tearDown()
        mockWebServer.shutdown()
    }

    @Test
    fun testGetPosts_withoutBefore_success() {
        var called = false
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(Fixture.postResponse.JSON)
        mockWebServer.enqueue(mockResponse)

        val repository = PostRepository(mockRetrofit, mockPostCloudRepository)

        repository.getPosts()
                .subscribe({ postResponse ->
                    assertThat(postResponse).isNotNull()
                    called = true
                }, mockOnError, mockOnSuccess)

        verifyZeroInteractions(mockOnError)
        verify(mockOnSuccess).invoke()
        verifyNoMoreInteractions(mockOnSuccess)
        assertThat(called).isTrue()
    }

    @Test
    fun testGetPosts_withoutBefore_error() {
        var called = false
        val mockResponse = MockResponse().setResponseCode(0)
                .setBody(Fixture.error.JSON)
        mockWebServer.enqueue(mockResponse)

        val repository = PostRepository(mockRetrofit, mockPostCloudRepository)

        repository.getPosts("Some Before")
                .subscribe(mockOnNext, { error ->
                    assertThat(error).isNotNull()
                    called = true
                }, mockOnSuccess)

        verifyZeroInteractions(mockOnNext)
        verifyZeroInteractions(mockOnSuccess)
        assertThat(called).isTrue()
    }

    @Test
    fun testGetPosts_withBefore_success() {
        var called = false
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(Fixture.postResponse.JSON)
        mockWebServer.enqueue(mockResponse)

        val repository = PostRepository(mockRetrofit, mockPostCloudRepository)

        repository.getPosts("Some Before")
                .subscribe({ postResponse ->
                    assertThat(postResponse).isNotNull()
                    called = true
                }, mockOnError, mockOnSuccess)

        verifyZeroInteractions(mockOnError)
        verify(mockOnSuccess).invoke()
        verifyNoMoreInteractions(mockOnSuccess)
        assertThat(called).isTrue()
    }

    @Test
    fun testGetPosts_withBefore_error() {
        var called = false
        val mockResponse = MockResponse().setResponseCode(0)
                .setBody(Fixture.error.JSON)
        mockWebServer.enqueue(mockResponse)

        val repository = PostRepository(mockRetrofit, mockPostCloudRepository)

        repository.getPosts()
                .subscribe(mockOnNext, { error ->
                    assertThat(error).isNotNull()
                    called = true
                }, mockOnSuccess)

        verifyZeroInteractions(mockOnNext)
        verifyZeroInteractions(mockOnSuccess)
        assertThat(called).isTrue()
    }

}
