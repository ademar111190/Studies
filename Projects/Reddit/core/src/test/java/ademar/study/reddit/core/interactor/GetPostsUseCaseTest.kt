package ademar.study.reddit.core.interactor

import ademar.study.reddit.core.repository.PostRepository
import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import io.reactivex.Observable
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

class GetPostsUseCaseTest : BaseTest() {

    @Mock lateinit var mockPostRepository: PostRepository

    @Mock lateinit var mockOnError: (Throwable) -> Unit
    @Mock lateinit var mockOnSuccess: () -> Unit

    @Test
    fun testExecute_success() {
        var called = false
        val useCase = GetPostsUseCase(mockPostRepository)
        val mockPost = Fixture.postResponse.makeModel()

        whenever(mockPostRepository.getPosts()).thenReturn(Observable.just(mockPost))

        useCase.execute()
                .subscribe({ post ->
                    assertThat(post).isNotNull()
                    called = true
                }, mockOnError, mockOnSuccess)

        verifyZeroInteractions(mockOnError)
        verify(mockOnSuccess).invoke()
        verifyNoMoreInteractions(mockOnSuccess)
        Assertions.assertThat(called).isTrue()
    }

    @Test
    fun testExecute_error() {
        val useCase = GetPostsUseCase(mockPostRepository)
        val mockPostError = Fixture.error.makeModel()

        whenever(mockPostRepository.getPosts()).thenReturn(Observable.error(mockPostError))

        useCase.execute()
                .test()
                .assertError(mockPostError)
    }

}
