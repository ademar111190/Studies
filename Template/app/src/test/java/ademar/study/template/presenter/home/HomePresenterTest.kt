package ademar.study.template.presenter.home

import ademar.study.template.core.interactor.GetHelloWorlds
import ademar.study.template.core.model.HelloWorld
import ademar.study.template.mapper.ErrorMapper
import ademar.study.template.mapper.HelloWorldMapper
import ademar.study.template.model.ErrorViewModel
import ademar.study.template.model.HelloWorldViewModel
import ademar.study.template.navigation.FlowController
import ademar.study.template.test.BaseTest
import ademar.study.template.test.Fixture
import com.nhaarman.mockito_kotlin.any
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
    @Mock lateinit var mockGetHelloWorlds: GetHelloWorlds
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

        mockHelloWorld = Fixture.helloWorld()
        mockError = Fixture.error()
        mockHelloWorldViewModel = Fixture.helloWorldViewModel()
        mockErrorViewModel = Fixture.errorViewModel()

        whenever(mockHelloWorldMapper.transform(mockHelloWorld)).thenReturn(mockHelloWorldViewModel)
        whenever(mockErrorMapper.transform(any())).thenReturn(mockErrorViewModel)

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
        val presenter = HomePresenter(mockFlowController, mockGetHelloWorlds, mockHelloWorldMapper, mockErrorMapper)
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

        whenever(mockGetHelloWorlds.execute()).thenReturn(Observable.just(mockHelloWorld))

        val presenter = HomePresenter(mockFlowController, mockGetHelloWorlds, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart()

        verify(mockGetHelloWorlds).execute()
        verifyNoMoreInteractions(mockGetHelloWorlds)
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

        whenever(mockGetHelloWorlds.execute()).thenReturn(Observable.error(mockError))

        val presenter = HomePresenter(mockFlowController, mockGetHelloWorlds, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onStart()

        verify(mockGetHelloWorlds).execute()
        verifyNoMoreInteractions(mockGetHelloWorlds)
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

        whenever(mockGetHelloWorlds.execute()).thenReturn(Observable.just(mockHelloWorld))

        val presenter = HomePresenter(mockFlowController, mockGetHelloWorlds, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        verify(mockGetHelloWorlds).execute()
        verifyNoMoreInteractions(mockGetHelloWorlds)
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

        whenever(mockGetHelloWorlds.execute()).thenReturn(Observable.error(mockError))

        val presenter = HomePresenter(mockFlowController, mockGetHelloWorlds, mockHelloWorldMapper, mockErrorMapper)
        presenter.onAttachView(stubView)
        presenter.onReloadClick()

        verify(mockGetHelloWorlds).execute()
        verifyNoMoreInteractions(mockGetHelloWorlds)
        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(clearHelloWorldsCount).isEqualTo(1)
        assertThat(showErrorCount).isEqualTo(1)
        assertThat(showRetryCount).isEqualTo(1)
    }

    @Test
    fun testOnHelloWorldClick() {
        val presenter = HomePresenter(mockFlowController, mockGetHelloWorlds, mockHelloWorldMapper, mockErrorMapper)
        presenter.onHelloWorldClick()
        verify(mockFlowController).launchDetail()
        verifyNoMoreInteractions(mockFlowController)
    }

    @Test
    fun testOnDetachView() {
        val presenter = HomePresenter(mockFlowController, mockGetHelloWorlds, mockHelloWorldMapper, mockErrorMapper)
        presenter.onDetachView()
    }

}
