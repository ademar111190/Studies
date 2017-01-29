package ademar.study.reddit.presenter.home

import ademar.study.reddit.core.interactor.GetPostsUseCase
import ademar.study.reddit.core.model.Error
import ademar.study.reddit.core.model.Post
import ademar.study.reddit.mapper.ErrorMapper
import ademar.study.reddit.mapper.home.PostMapper
import ademar.study.reddit.model.ErrorViewModel
import ademar.study.reddit.model.home.PostViewModel
import ademar.study.reddit.test.BaseTest
import ademar.study.reddit.test.Fixture
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever

class HomePresenterTest : BaseTest() {

    @Mock lateinit var mockGetPostsUseCase: GetPostsUseCase
    @Mock lateinit var mockPostMapper: PostMapper
    @Mock lateinit var mockErrorMapper: ErrorMapper

    private lateinit var mockPost: Post
    private lateinit var mockError: Error
    private lateinit var mockPostViewModel: PostViewModel
    private lateinit var mockErrorViewModel: ErrorViewModel

    private var bindPostCount = 0
    private var showContentCount = 0
    private var showErrorCount = 0
    private var showLoadingCount = 0
    private var showRetryCount = 0
    private var hideUnloadedErrorCount = 0
    private var showUnloadedPostsCount = 0
    private var hideUnloadedPostsCount = 0
    private var showUnloadedErrorCount = 0
    private var clearPostsCount = 0

    @Before
    override fun setUp() {
        super.setUp()

        mockPost = Fixture.post.makeModel()
        mockError = Fixture.error.makeModel()
        mockPostViewModel = Fixture.postViewModel.makeModel()
        mockErrorViewModel = Fixture.errorViewModel.makeModel()

        whenever(mockPostMapper.transform(mockPost)).thenReturn(mockPostViewModel)
        whenever(mockErrorMapper.transform(mockError)).thenReturn(mockErrorViewModel)

        bindPostCount = 0
        showContentCount = 0
        showErrorCount = 0
        showLoadingCount = 0
        showRetryCount = 0
        hideUnloadedErrorCount = 0
        showUnloadedPostsCount = 0
        hideUnloadedPostsCount = 0
        showUnloadedErrorCount = 0
        clearPostsCount = 0
    }

    @Test
    fun testOnAttachView() {
        val stubView = object : StubHomeView() {}
        val presenter = HomePresenter(mockGetPostsUseCase, mockPostMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
    }

    @Test
    fun testOnStart_success() {
        val stubView = object : StubHomeView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun bindPost(viewModel: PostViewModel) {
                assertThat(viewModel).isEqualTo(mockPostViewModel)
                bindPostCount++
            }

            override fun showContent() {
                showContentCount++
            }

            override fun clearPosts() {
                clearPostsCount++
            }
        }

        whenever(mockGetPostsUseCase.currentPage()).thenReturn(Observable.just(mockPost))

        val presenter = HomePresenter(mockGetPostsUseCase, mockPostMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart()

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(bindPostCount).isEqualTo(1)
        assertThat(showContentCount).isEqualTo(1)
        assertThat(clearPostsCount).isEqualTo(1)
    }

    @Test
    fun testOnStart_error() {
        val stubView = object : StubHomeView() {
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

            override fun clearPosts() {
                clearPostsCount++
            }
        }

        whenever(mockGetPostsUseCase.currentPage()).thenReturn(Observable.error(mockError))

        val presenter = HomePresenter(mockGetPostsUseCase, mockPostMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart()

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(showErrorCount).isEqualTo(1)
        assertThat(showRetryCount).isEqualTo(1)
        assertThat(clearPostsCount).isEqualTo(1)
    }

    @Test
    fun testOnReloadClick_success() {
        val stubView = object : StubHomeView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun bindPost(viewModel: PostViewModel) {
                assertThat(viewModel).isEqualTo(mockPostViewModel)
                bindPostCount++
            }

            override fun showContent() {
                showContentCount++
            }

            override fun clearPosts() {
                clearPostsCount++
            }
        }

        whenever(mockGetPostsUseCase.currentPage()).thenReturn(Observable.just(mockPost))

        val presenter = HomePresenter(mockGetPostsUseCase, mockPostMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(bindPostCount).isEqualTo(1)
        assertThat(showContentCount).isEqualTo(1)
        assertThat(clearPostsCount).isEqualTo(1)
    }

    @Test
    fun testOnReloadClick_error() {
        val stubView = object : StubHomeView() {
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

            override fun clearPosts() {
                clearPostsCount++
            }
        }

        whenever(mockGetPostsUseCase.currentPage()).thenReturn(Observable.error(mockError))

        val presenter = HomePresenter(mockGetPostsUseCase, mockPostMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(showErrorCount).isEqualTo(1)
        assertThat(showRetryCount).isEqualTo(1)
        assertThat(clearPostsCount).isEqualTo(1)
    }

    @Test
    fun testOnPreviousPageClick_success() {
        val stubView = object : StubHomeView() {
            override fun hideUnloadedError() {
                hideUnloadedErrorCount++
            }

            override fun showUnloadedPosts() {
                showUnloadedPostsCount++
            }

            override fun bindPost(viewModel: PostViewModel) {
                assertThat(viewModel).isEqualTo(mockPostViewModel)
                bindPostCount++
            }
        }

        whenever(mockGetPostsUseCase.previousPage()).thenReturn(Observable.just(mockPost))

        val presenter = HomePresenter(mockGetPostsUseCase, mockPostMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onPreviousPageClick()

        assertThat(hideUnloadedErrorCount).isEqualTo(1)
        assertThat(showUnloadedPostsCount).isEqualTo(1)
        assertThat(bindPostCount).isEqualTo(1)
    }

    @Test
    fun testOnPreviousPageClick_error() {
        val stubView = object : StubHomeView() {
            override fun hideUnloadedError() {
                hideUnloadedErrorCount++
            }

            override fun showUnloadedPosts() {
                showUnloadedPostsCount++
            }

            override fun showUnloadedError(viewModel: ErrorViewModel) {
                assertThat(viewModel).isEqualTo(mockErrorViewModel)
                showUnloadedErrorCount++
            }
        }

        whenever(mockGetPostsUseCase.previousPage()).thenReturn(Observable.error(mockError))

        val presenter = HomePresenter(mockGetPostsUseCase, mockPostMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onPreviousPageClick()

        assertThat(hideUnloadedErrorCount).isEqualTo(1)
        assertThat(showUnloadedPostsCount).isEqualTo(1)
        assertThat(showUnloadedErrorCount).isEqualTo(1)
    }

    @Test
    fun testOnDetachView() {
        val presenter = HomePresenter(mockGetPostsUseCase, mockPostMapper, mockErrorMapper)
        presenter.onDetachView()
    }

}
