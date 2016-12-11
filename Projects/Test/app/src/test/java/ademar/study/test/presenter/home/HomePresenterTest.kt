package ademar.study.test.presenter.home

import ademar.study.test.core.interactor.GetHelloWorldUseCase
import ademar.study.test.core.model.Error
import ademar.study.test.core.model.HelloWorld
import ademar.study.test.test.BaseTest
import ademar.study.test.test.Fixture
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

    private lateinit var mockHelloWorld: HelloWorld
    private lateinit var mockHelloWorldError: Error

    private var bindHelloWorldCount = 0
    private var showContentCount = 0
    private var showErrorCount = 0
    private var showLoadingCount = 0
    private var showRetryCount = 0

    @Before
    override fun setUp() {
        super.setUp()

        mockHelloWorld = Fixture.helloWorld.makeModel()
        mockHelloWorldError = Fixture.error.makeModel()

        bindHelloWorldCount = 0
        showContentCount = 0
        showErrorCount = 0
        showLoadingCount = 0
        showRetryCount = 0
    }

    @Test
    fun testOnAttachView() {
        val stubView = object : StubHomeView() {}
        val presenter = HomePresenter(mockGetHelloWorldUseCase)
        presenter.onAttachView(stubView)
    }

    @Test
    fun testOnStart_success() {
        val stubView = object : StubHomeView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun bindHelloWorld(helloWorld: HelloWorld) {
                assertThat(helloWorld).isEqualTo(mockHelloWorld)
                bindHelloWorldCount++
            }

            override fun showContent() {
                showContentCount++
            }
        }

        whenever(mockGetHelloWorldUseCase.execute()).thenReturn(Observable.just(mockHelloWorld))

        val presenter = HomePresenter(mockGetHelloWorldUseCase)
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

            override fun showError(error: Error) {
                assertThat(error).isEqualTo(mockHelloWorldError)
                showErrorCount++
            }

            override fun showRetry() {
                showRetryCount++
            }
        }

        whenever(mockGetHelloWorldUseCase.execute()).thenReturn(Observable.error(mockHelloWorldError))

        val presenter = HomePresenter(mockGetHelloWorldUseCase)
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

            override fun bindHelloWorld(helloWorld: HelloWorld) {
                assertThat(helloWorld).isEqualTo(mockHelloWorld)
                bindHelloWorldCount++
            }

            override fun showContent() {
                showContentCount++
            }
        }

        whenever(mockGetHelloWorldUseCase.execute()).thenReturn(Observable.just(mockHelloWorld))

        val presenter = HomePresenter(mockGetHelloWorldUseCase)
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

            override fun showError(error: Error) {
                assertThat(error).isEqualTo(mockHelloWorldError)
                showErrorCount++
            }

            override fun showRetry() {
                showRetryCount++
            }
        }

        whenever(mockGetHelloWorldUseCase.execute()).thenReturn(Observable.error(mockHelloWorldError))

        val presenter = HomePresenter(mockGetHelloWorldUseCase)
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
        val presenter = HomePresenter(mockGetHelloWorldUseCase)
        presenter.onDetachView()
    }

}
