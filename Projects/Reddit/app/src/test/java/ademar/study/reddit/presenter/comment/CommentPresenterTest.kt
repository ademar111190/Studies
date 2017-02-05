package ademar.study.reddit.presenter.comment

import ademar.study.reddit.core.interactor.GetCommentsUseCase
import ademar.study.reddit.core.model.Comment
import ademar.study.reddit.core.model.Error
import ademar.study.reddit.mapper.ErrorMapper
import ademar.study.reddit.mapper.comment.CommentMapper
import ademar.study.reddit.model.ErrorViewModel
import ademar.study.reddit.model.comment.CommentViewModel
import ademar.study.reddit.test.BaseTest
import ademar.study.reddit.test.Fixture
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever

class CommentPresenterTest : BaseTest() {

    @Mock lateinit var mockGetCommentsUseCase: GetCommentsUseCase
    @Mock lateinit var mockCommentMapper: CommentMapper
    @Mock lateinit var mockErrorMapper: ErrorMapper

    private lateinit var mockComment: Comment
    private lateinit var mockError: Error
    private lateinit var mockCommentViewModel: CommentViewModel
    private lateinit var mockErrorViewModel: ErrorViewModel

    private val mockLink = "http://some.link"

    private var bindCommentCount = 0
    private var showContentCount = 0
    private var showErrorCount = 0
    private var showLoadingCount = 0
    private var showRetryCount = 0
    private var showEmptyCount = 0
    private var clearCommentsCount = 0

    @Before
    override fun setUp() {
        super.setUp()

        mockComment = Fixture.comment.makeModel()
        mockError = Fixture.error.makeModel()
        mockCommentViewModel = Fixture.commentViewModel.makeModel()
        mockErrorViewModel = Fixture.errorViewModel.makeModel()

        whenever(mockCommentMapper.transform(mockComment)).thenReturn(mockCommentViewModel)
        whenever(mockErrorMapper.transform(mockError)).thenReturn(mockErrorViewModel)

        bindCommentCount = 0
        showContentCount = 0
        showErrorCount = 0
        showLoadingCount = 0
        showRetryCount = 0
        showEmptyCount = 0
        clearCommentsCount = 0
    }

    @Test
    fun testOnAttachView() {
        val stubView = object : StubCommentView() {}
        val presenter = CommentPresenter(mockGetCommentsUseCase, mockCommentMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
    }

    @Test
    fun testOnStart_success() {
        val stubView = object : StubCommentView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun bindComment(viewModel: CommentViewModel) {
                assertThat(viewModel).isEqualTo(mockCommentViewModel)
                bindCommentCount++
            }

            override fun showContent() {
                showContentCount++
            }

            override fun clearComments() {
                clearCommentsCount++
            }
        }

        whenever(mockGetCommentsUseCase.getComments(mockLink)).thenReturn(Observable.just(mockComment))

        val presenter = CommentPresenter(mockGetCommentsUseCase, mockCommentMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart(mockLink)

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(bindCommentCount).isEqualTo(1)
        assertThat(showContentCount).isEqualTo(1)
        assertThat(clearCommentsCount).isEqualTo(1)
    }

    @Test
    fun testOnStart_error() {
        val stubView = object : StubCommentView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun showError(viewModel: ErrorViewModel) {
                assertThat(viewModel).isEqualTo(mockErrorViewModel)
                showErrorCount++
            }

            override fun showRetry() {
                showRetryCount++
            }

            override fun clearComments() {
                clearCommentsCount++
            }
        }

        whenever(mockGetCommentsUseCase.getComments(mockLink)).thenReturn(Observable.error(mockError))

        val presenter = CommentPresenter(mockGetCommentsUseCase, mockCommentMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart(mockLink)

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(showErrorCount).isEqualTo(1)
        assertThat(showRetryCount).isEqualTo(1)
        assertThat(clearCommentsCount).isEqualTo(1)
    }

    @Test
    fun testOnStart_emptyLink() {
        val stubView = object : StubCommentView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun showRetry() {
                showRetryCount++
            }

            override fun clearComments() {
                clearCommentsCount++
            }
        }

        val presenter = CommentPresenter(mockGetCommentsUseCase, mockCommentMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart("")

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(showRetryCount).isEqualTo(1)
        assertThat(clearCommentsCount).isEqualTo(1)
    }

    @Test
    fun testOnStart_empty() {
        val stubView = object : StubCommentView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun showEmpty() {
                showEmptyCount++
            }

            override fun clearComments() {
                clearCommentsCount++
            }
        }

        whenever(mockGetCommentsUseCase.getComments(mockLink)).thenReturn(Observable.empty())

        val presenter = CommentPresenter(mockGetCommentsUseCase, mockCommentMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart(mockLink)

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(showEmptyCount).isEqualTo(1)
        assertThat(clearCommentsCount).isEqualTo(1)
    }

    @Test
    fun testOnReloadClick_success() {
        val stubView = object : StubCommentView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun bindComment(viewModel: CommentViewModel) {
                assertThat(viewModel).isEqualTo(mockCommentViewModel)
                bindCommentCount++
            }

            override fun showContent() {
                showContentCount++
            }

            override fun clearComments() {
                clearCommentsCount++
            }
        }

        whenever(mockGetCommentsUseCase.getComments(mockLink)).thenReturn(Observable.just(mockComment))

        val presenter = CommentPresenter(mockGetCommentsUseCase, mockCommentMapper, mockErrorMapper)
        presenter.onStart(mockLink)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(bindCommentCount).isEqualTo(1)
        assertThat(showContentCount).isEqualTo(1)
        assertThat(clearCommentsCount).isEqualTo(1)
    }

    @Test
    fun testOnReloadClick_error() {
        val stubView = object : StubCommentView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun showError(viewModel: ErrorViewModel) {
                assertThat(viewModel).isEqualTo(mockErrorViewModel)
                showErrorCount++
            }

            override fun showRetry() {
                showRetryCount++
            }

            override fun clearComments() {
                clearCommentsCount++
            }
        }

        whenever(mockGetCommentsUseCase.getComments(mockLink)).thenReturn(Observable.error(mockError))

        val presenter = CommentPresenter(mockGetCommentsUseCase, mockCommentMapper, mockErrorMapper)
        presenter.onStart(mockLink)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(showErrorCount).isEqualTo(1)
        assertThat(showRetryCount).isEqualTo(1)
        assertThat(clearCommentsCount).isEqualTo(1)
    }

    @Test
    fun testOnReload_emptyLink() {
        val stubView = object : StubCommentView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun showRetry() {
                showRetryCount++
            }

            override fun clearComments() {
                clearCommentsCount++
            }
        }

        val presenter = CommentPresenter(mockGetCommentsUseCase, mockCommentMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(showRetryCount).isEqualTo(1)
        assertThat(clearCommentsCount).isEqualTo(1)
    }

    @Test
    fun testOnReloadClick_empty() {
        val stubView = object : StubCommentView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun showEmpty() {
                showEmptyCount++
            }

            override fun clearComments() {
                clearCommentsCount++
            }
        }

        whenever(mockGetCommentsUseCase.getComments(mockLink)).thenReturn(Observable.empty())

        val presenter = CommentPresenter(mockGetCommentsUseCase, mockCommentMapper, mockErrorMapper)
        presenter.onStart(mockLink)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(showEmptyCount).isEqualTo(1)
        assertThat(clearCommentsCount).isEqualTo(1)
    }

    @Test
    fun testOnDetachView() {
        val presenter = CommentPresenter(mockGetCommentsUseCase, mockCommentMapper, mockErrorMapper)
        presenter.onDetachView()
    }

}
