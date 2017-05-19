package ademar.study.test.presenter.home

import ademar.study.test.core.interactor.GetAllHelloWorldUseCase
import ademar.study.test.core.model.Error
import ademar.study.test.core.model.HelloWorld
import ademar.study.test.mapper.ErrorMapper
import ademar.study.test.mapper.HelloWorldMapper
import ademar.study.test.model.ErrorViewModel
import ademar.study.test.model.HelloWorldViewModel
import ademar.study.test.navigation.FlowController
import ademar.study.test.test.BaseTest
import ademar.study.test.test.Fixture
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class HomePresenterTest : BaseTest() {

    @Mock lateinit var mockFlowController: FlowController
    @Mock lateinit var mockGetAllHelloWorldUseCase: GetAllHelloWorldUseCase
    @Mock lateinit var mockHelloWorldMapper: HelloWorldMapper
    @Mock lateinit var mockErrorMapper: ErrorMapper

    private lateinit var mockHelloWorld: HelloWorld
    private lateinit var mockError: Error
    private lateinit var mockHelloWorldViewModel: HelloWorldViewModel
    private lateinit var mockErrorViewModel: ErrorViewModel

    private var clearHelloWorldsCount = 0
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

        clearHelloWorldsCount = 0
        bindHelloWorldCount = 0
        showContentCount = 0
        showErrorCount = 0
        showLoadingCount = 0
        showRetryCount = 0
    }

    @Test
    fun testOnAttachView() {
        val stubView = object : StubHomeView() {}
        val presenter = HomePresenter(mockFlowController, mockGetAllHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
    }

    @Test
    fun testOnStart_success() {
        val stubView = object : StubHomeView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun clearHelloWorlds() {
                clearHelloWorldsCount++
            }

            override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
                assertThat(viewModel).isEqualTo(mockHelloWorldViewModel)
                bindHelloWorldCount++
            }

            override fun showContent() {
                showContentCount++
            }
        }

        whenever(mockGetAllHelloWorldUseCase.execute()).thenReturn(Observable.just(mockHelloWorld))

        val presenter = HomePresenter(mockFlowController, mockGetAllHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart()

        verify(mockGetAllHelloWorldUseCase).execute()
        verifyNoMoreInteractions(mockGetAllHelloWorldUseCase)
        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(clearHelloWorldsCount).isEqualTo(1)
        assertThat(bindHelloWorldCount).isEqualTo(1)
        assertThat(showContentCount).isEqualTo(1)
    }

    @Test
    fun testOnStart_error() {
        val stubView = object : StubHomeView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun clearHelloWorlds() {
                clearHelloWorldsCount++
            }

            override fun showError(viewModel: ErrorViewModel) {
                assertThat(viewModel).isEqualTo(mockErrorViewModel)
                showErrorCount++
            }

            override fun showRetry() {
                showRetryCount++
            }
        }

        whenever(mockGetAllHelloWorldUseCase.execute()).thenReturn(Observable.error(mockError))

        val presenter = HomePresenter(mockFlowController, mockGetAllHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart()

        verify(mockGetAllHelloWorldUseCase).execute()
        verifyNoMoreInteractions(mockGetAllHelloWorldUseCase)
        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(clearHelloWorldsCount).isEqualTo(1)
        assertThat(showErrorCount).isEqualTo(1)
        assertThat(showRetryCount).isEqualTo(1)
    }

    @Test
    fun testOnReloadClick_success() {
        val stubView = object : StubHomeView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun clearHelloWorlds() {
                clearHelloWorldsCount++
            }

            override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
                assertThat(viewModel).isEqualTo(mockHelloWorldViewModel)
                bindHelloWorldCount++
            }

            override fun showContent() {
                showContentCount++
            }
        }

        whenever(mockGetAllHelloWorldUseCase.execute()).thenReturn(Observable.just(mockHelloWorld))

        val presenter = HomePresenter(mockFlowController, mockGetAllHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        verify(mockGetAllHelloWorldUseCase).execute()
        verifyNoMoreInteractions(mockGetAllHelloWorldUseCase)
        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(clearHelloWorldsCount).isEqualTo(1)
        assertThat(bindHelloWorldCount).isEqualTo(1)
        assertThat(showContentCount).isEqualTo(1)
    }

    @Test
    fun testOnReloadClick_error() {
        val stubView = object : StubHomeView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun clearHelloWorlds() {
                clearHelloWorldsCount++
            }

            override fun showError(viewModel: ErrorViewModel) {
                assertThat(viewModel).isEqualTo(mockErrorViewModel)
                showErrorCount++
            }

            override fun showRetry() {
                showRetryCount++
            }
        }

        whenever(mockGetAllHelloWorldUseCase.execute()).thenReturn(Observable.error(mockError))

        val presenter = HomePresenter(mockFlowController, mockGetAllHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        verify(mockGetAllHelloWorldUseCase).execute()
        verifyNoMoreInteractions(mockGetAllHelloWorldUseCase)
        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(clearHelloWorldsCount).isEqualTo(1)
        assertThat(showErrorCount).isEqualTo(1)
        assertThat(showRetryCount).isEqualTo(1)
    }

    @Test
    fun testOnHelloWorldClick() {
        val presenter = HomePresenter(mockFlowController, mockGetAllHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onHelloWorldClick()
        verify(mockFlowController).launchDetail()
        verifyNoMoreInteractions(mockFlowController)
    }

    @Test
    fun testOnDetachView() {
        val presenter = HomePresenter(mockFlowController, mockGetAllHelloWorldUseCase, mockHelloWorldMapper, mockErrorMapper)
        presenter.onDetachView()
    }

}
