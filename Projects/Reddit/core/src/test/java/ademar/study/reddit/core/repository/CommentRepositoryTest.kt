package ademar.study.reddit.core.repository

import ademar.study.reddit.core.model.internal.PostDetailResponse
import ademar.study.reddit.core.repository.datasource.CommentCloudRepository
import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import retrofit2.Retrofit
import org.mockito.Mockito.`when` as whenever

class CommentRepositoryTest : BaseTest() {

    @Mock lateinit var mockOnNext: (List<PostDetailResponse>) -> Unit
    @Mock lateinit var mockOnError: (Throwable) -> Unit
    @Mock lateinit var mockOnSuccess: () -> Unit

    private lateinit var mockRetrofit: Retrofit
    private lateinit var mockCommentCloudRepository: CommentCloudRepository

    private val mockLink = "/some/link"

    override fun setUp() {
        super.setUp()
        mockWebServer.start()
        mockContext = coreMockModule.provideContext()
        val mockHttpLoggingInterceptor = coreMockModule.provideHttpLoggingInterceptor()
        val mockOkHttpClient = coreMockModule.provideOkHttpClient(mockHttpLoggingInterceptor)
        mockRetrofit = coreMockModule.provideRetrofit(mockOkHttpClient, mockStandardErrors)
        mockCommentCloudRepository = coreMockModule.provideCommentCloudRepository(mockRetrofit)
    }

    override fun tearDown() {
        super.tearDown()
        mockWebServer.shutdown()
    }

    @Test
    fun testGetComments_success() {
        var called = false
        val mockResponse = MockResponse().setResponseCode(200)
                .setBody(Fixture.postDetailResponse.SERVICE_JSON)
        mockWebServer.enqueue(mockResponse)

        val repository = CommentRepository(mockRetrofit, mockCommentCloudRepository)

        repository.getComments(mockLink)
                .subscribe({ postDetailResponses ->
                    assertThat(postDetailResponses.size).isEqualTo(2)
                    called = true
                }, mockOnError, mockOnSuccess)

        verifyZeroInteractions(mockOnError)
        verify(mockOnSuccess).invoke()
        verifyNoMoreInteractions(mockOnSuccess)
        assertThat(called).isTrue()
    }

    @Test
    fun testGetComments_error() {
        var called = false
        val mockResponse = MockResponse().setResponseCode(0)
                .setBody(Fixture.error.JSON)
        mockWebServer.enqueue(mockResponse)

        val repository = CommentRepository(mockRetrofit, mockCommentCloudRepository)

        repository.getComments(mockLink)
                .subscribe(mockOnNext, { error ->
                    assertThat(error).isNotNull()
                    called = true
                }, mockOnSuccess)

        verifyZeroInteractions(mockOnNext)
        verifyZeroInteractions(mockOnSuccess)
        assertThat(called).isTrue()
    }

}
