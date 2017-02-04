package ademar.study.reddit.core.interactor

import ademar.study.reddit.core.model.mapper.CommentMapper
import ademar.study.reddit.core.repository.CommentRepository
import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import io.reactivex.Observable
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

class GetCommentsUseCaseTest : BaseTest() {

    @Mock lateinit var mockCommentRepository: CommentRepository

    @Mock lateinit var mockOnError: (Throwable) -> Unit
    @Mock lateinit var mockOnSuccess: () -> Unit

    private val mockLink = "/some/link"

    @Test
    fun testGetComments_success() {
        var called = false
        val useCase = GetCommentsUseCase(mockCommentRepository, CommentMapper())
        val mockComments = listOf(Fixture.postDetailResponse.makeModel())

        whenever(mockCommentRepository.getComments(mockLink)).thenReturn(Observable.just(mockComments))

        useCase.getComments(mockLink)
                .subscribe({ comments ->
                    assertThat(comments).isNotNull()
                    called = true
                }, mockOnError, mockOnSuccess)

        verifyZeroInteractions(mockOnError)
        verify(mockOnSuccess).invoke()
        verifyNoMoreInteractions(mockOnSuccess)
        Assertions.assertThat(called).isTrue()
    }

    @Test
    fun testGetComments_error() {
        val useCase = GetCommentsUseCase(mockCommentRepository, CommentMapper())
        val mockPostError = Fixture.error.makeModel()

        whenever(mockCommentRepository.getComments(mockLink)).thenReturn(Observable.error(mockPostError))

        useCase.getComments(mockLink)
                .test()
                .assertError(mockPostError)
    }

}
