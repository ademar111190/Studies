package ademar.study.template.presenter.detail

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.mapper.DetailMapper
import ademar.study.template.mapper.ErrorMapper
import ademar.study.template.model.DetailViewModel
import ademar.study.template.model.ErrorViewModel
import ademar.study.template.test.BaseTest
import ademar.study.template.test.Fixture
import ademar.study.template.test.Fixture.detailViewModel
import ademar.study.template.test.Fixture.errorViewModel
import ademar.study.template.test.Fixture.helloWorld
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class DetailPresenterTest : BaseTest() {

    @Mock lateinit var mockDetailMapper: DetailMapper
    @Mock lateinit var mockErrorMapper: ErrorMapper

    private lateinit var mockHelloWorld: HelloWorld
    private lateinit var mockError: Error
    private lateinit var mockDetailViewModel: DetailViewModel
    private lateinit var mockErrorViewModel: ErrorViewModel

    private var bindHelloWorldCount = 0
    private var showContentCount = 0
    private var showErrorCount = 0
    private var showLoadingCount = 0
    private var showRetryCount = 0

    @Before
    override fun setUp() {
        super.setUp()

        mockHelloWorld = helloWorld()
        mockError = Fixture.error()
        mockDetailViewModel = detailViewModel()
        mockErrorViewModel = errorViewModel()

        whenever(mockDetailMapper.transform(mockHelloWorld, listOf(mockHelloWorld))).thenReturn(mockDetailViewModel)
        whenever(mockErrorMapper.transform(any())).thenReturn(mockErrorViewModel)

        bindHelloWorldCount = 0
        showContentCount = 0
        showErrorCount = 0
        showLoadingCount = 0
        showRetryCount = 0
    }

    @Test
    fun testOnAttachView() {
        val stubView = object : StubDetailView() {}
        val presenter = DetailPresenter(mockDetailMapper, mockErrorMapper, helloWorld(), listOf(helloWorld()))
        presenter.onAttachView(stubView)
    }

    @Test
    fun testOnStart_success() {
        val stubView = object : StubDetailView() {
            override fun showLoading() {
                showLoadingCount++
            }

            override fun bindDetail(viewModel: DetailViewModel) {
                assertThat(viewModel).isEqualTo(mockDetailViewModel)
                bindHelloWorldCount++
            }

            override fun showContent() {
                showContentCount++
            }
        }

        val presenter = DetailPresenter(mockDetailMapper, mockErrorMapper, helloWorld(), listOf(helloWorld()))
        presenter.onAttachView(stubView)
        presenter.onStart()

        assertThat(showLoadingCount).isEqualTo(1)
        assertThat(bindHelloWorldCount).isEqualTo(1)
        assertThat(showContentCount).isEqualTo(1)
    }

    @Test
    fun testOnDetachView() {
        val presenter = DetailPresenter(mockDetailMapper, mockErrorMapper, helloWorld(), listOf(helloWorld()))
        presenter.onDetachView()
    }

}
