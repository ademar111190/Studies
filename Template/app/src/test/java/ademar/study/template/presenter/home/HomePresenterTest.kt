package ademar.study.template.presenter.home

import ademar.study.template.core.interactor.GetHelloWorldUseCase
import ademar.study.template.core.model.Error
import ademar.study.template.core.model.HelloWorld
import ademar.study.template.mapper.ErrorMapper
import ademar.study.template.mapper.home.HelloWorldMapper
import ademar.study.template.model.ErrorViewModel
import ademar.study.template.model.home.HelloWorldViewModel
import ademar.study.template.test.BaseTest
import ademar.study.template.test.Fixture
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when` as whenever

class HomePresenterTest : BaseTest() {

    @Mock lateinit var mockGetHelloWorldUseCase: GetHelloWorldUseCase
    @Mock lateinit var mockHelloWorldMapper: HelloWorldMapper
    @Mock lateinit var mockErrorMapper: ErrorMapper

    private lateinit var mockHelloWorld: HelloWorld
    private lateinit var mockError: Error
    private lateinit var mockHelloWorldViewModel: HelloWorldViewModel
    private lateinit var mockErrorViewModel: ErrorViewModel

    private var bindHelloWorldCount = 0
    private var showContentCount = 0
    private var showErrorCount = 0
    private var showLoadingCount = 0
    private var showRetryCount = 0

    @Before
    override fun setUp() {
        super.setUp()

        mockHelloWorld = Fixture.helloWorld.makeModel()
        mockError = Fixture.error.makeModel()
        mockHelloWorldViewModel = Fixture.helloWorldViewModel.makeModel()
        mockErrorViewModel = Fixture.errorViewModel.makeModel()

        whenever(mockHelloWorldMapper.transform(mockHelloWorld)).thenReturn(mockHelloWorldViewModel)
        whenever(mockErrorMapper.transform(mockError)).thenReturn(mockErrorViewModel)

        bindHelloWorldCount = 0
        showContentCount = 0
        showErrorCount = 0
        showLoadingCount = 0
        showRetryCount = 0
    }

    @Test
    fun testOnAttachView() {
        val stubView = object : StubHomeView() {}
        val presenter = HomePresenter(mockGetHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
    }

    @Test
    fun testOnStart_success() {
        val stubView = object : StubHomeView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
                assertThat(viewModel).isEqualTo(mockHelloWorldViewModel)
                bindHelloWorldCount++
            }

            override fun showContent() {
                showContentCount++
            }
        }

        whenever(mockGetHelloWorldUseCase.execute()).thenReturn(Observable.just(mockHelloWorld))

        val presenter = HomePresenter(mockGetHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart()

        verify(mockGetHelloWorldUseCase).execute()
        verifyNoMoreInteractions(mockGetHelloWorldUseCase)
        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(bindHelloWorldCount).isEqualTo(1)
        assertThat(showContentCount).isEqualTo(1)
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
        }

        whenever(mockGetHelloWorldUseCase.execute()).thenReturn(Observable.error(mockError))

        val presenter = HomePresenter(mockGetHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart()

        verify(mockGetHelloWorldUseCase).execute()
        verifyNoMoreInteractions(mockGetHelloWorldUseCase)
        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(showErrorCount).isEqualTo(1)
        assertThat(showRetryCount).isEqualTo(1)
    }

    @Test
    fun testOnReloadClick_success() {
        val stubView = object : StubHomeView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
                assertThat(viewModel).isEqualTo(mockHelloWorldViewModel)
                bindHelloWorldCount++
            }

            override fun showContent() {
                showContentCount++
            }
        }

        whenever(mockGetHelloWorldUseCase.execute()).thenReturn(Observable.just(mockHelloWorld))

        val presenter = HomePresenter(mockGetHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        verify(mockGetHelloWorldUseCase).execute()
        verifyNoMoreInteractions(mockGetHelloWorldUseCase)
        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(bindHelloWorldCount).isEqualTo(1)
        assertThat(showContentCount).isEqualTo(1)
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
        }

        whenever(mockGetHelloWorldUseCase.execute()).thenReturn(Observable.error(mockError))

        val presenter = HomePresenter(mockGetHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        verify(mockGetHelloWorldUseCase).execute()
        verifyNoMoreInteractions(mockGetHelloWorldUseCase)
        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(showErrorCount).isEqualTo(1)
        assertThat(showRetryCount).isEqualTo(1)
    }

    @Test
    fun testOnDetachView() {
        val presenter = HomePresenter(mockGetHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onDetachView()
    }

}
