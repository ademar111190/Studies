package ademar.study.template.core.ext

import ademar.study.template.core.model.Error
import ademar.study.template.core.model.StandardErrors
import ademar.study.template.core.test.BaseTest
import ademar.study.template.core.test.Fixture
import com.nhaarman.mockito_kotlin.whenever
import okhttp3.ResponseBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit

class RetrofitTest : BaseTest() {

    private var nextCalls = 0
    private var errorCalled = false
    private var successCalled = false

    @Mock lateinit var mockResponseBody: ResponseBody
    @Mock lateinit var mockRetrofit: Retrofit
    @Mock lateinit var mockResponse: Response<String>
    @Mock lateinit var mockConverter: Converter<ResponseBody, Error>

    @Before
    override fun setUp() {
        super.setUp()
        nextCalls = 0
        errorCalled = false
        successCalled = false
        mockRetrofit.standardErrors = mockStandardErrors
    }

    @Test
    fun testObserveBody_200() {
        val foo = "Foo"
        whenever(mockResponse.code()).thenReturn(200)
        whenever(mockResponse.body()).thenReturn(foo)

        mockRetrofit.observeBody(mockResponse)
                .subscribe({
                    assertThat(it).isEqualTo(foo)
                    nextCalls++
                }, {
                    errorCalled = true
                }, {
                    successCalled = true
                })

        assertThat(nextCalls).isEqualTo(1)
        assertThat(errorCalled).isFalse()
        assertThat(successCalled).isTrue()

        verify(mockResponse).code()
        verify(mockResponse).body()
        verifyNoMoreInteractions(mockResponse)
    }

    @Test
    fun testObserveBody_401() {
        whenever(mockResponse.code()).thenReturn(401)

        mockRetrofit.observeBody(mockResponse)
                .subscribe({
                    nextCalls++
                }, { e ->
                    assertThat(StandardErrors(mockContext).toError(e)).isEqualTo(mockErrorUnauthorized)
                    errorCalled = true
                }, {
                    successCalled = true
                })

        assertThat(nextCalls).isEqualTo(0)
        assertThat(errorCalled).isTrue()
        assertThat(successCalled).isFalse()

        verify(mockResponse).code()
        verifyNoMoreInteractions(mockResponse)
    }

    @Test
    fun testObserveBody_error() {
        val errorModel = Fixture.error.makeModel()
        whenever(mockResponse.code()).thenReturn(0)
        whenever(mockRetrofit.responseBodyConverter<Error>(any(), any())).thenReturn(mockConverter)
        whenever(mockResponse.errorBody()).thenReturn(mockResponseBody)
        whenever(mockConverter.convert(mockResponseBody)).thenReturn(errorModel)

        mockRetrofit.observeBody(mockResponse)
                .subscribe({
                    nextCalls++
                }, { e ->
                    assertThat(StandardErrors(mockContext).toError(e)).isEqualTo(errorModel)
                    errorCalled = true
                }, {
                    successCalled = true
                })

        assertThat(nextCalls).isEqualTo(0)
        assertThat(errorCalled).isTrue()
        assertThat(successCalled).isFalse()

        verify(mockResponse).code()
        verify(mockResponse).errorBody()
        verifyNoMoreInteractions(mockResponse)
        verifyZeroInteractions(mockResponseBody)
        verify(mockConverter).convert(mockResponseBody)
        verifyNoMoreInteractions(mockConverter)
    }

    @Test
    fun testObserveBody_exception() {
        whenever(mockResponse.code()).thenReturn(0)
        whenever(mockRetrofit.responseBodyConverter<Error>(any(), any())).thenReturn(null)

        mockRetrofit.observeBody(mockResponse)
                .subscribe({
                    nextCalls++
                }, { e ->
                    assertThat(StandardErrors(mockContext).toError(e)).isEqualTo(mockErrorUnknown)
                    errorCalled = true
                }, {
                    successCalled = true
                })

        assertThat(nextCalls).isEqualTo(0)
        assertThat(errorCalled).isTrue()
        assertThat(successCalled).isFalse()

        verify(mockResponse).code()
        verify(mockResponse).errorBody()
        verifyNoMoreInteractions(mockResponse)
    }

    @Test
    fun testObserveBody_null() {
        val mockJson = ""
        whenever(mockResponse.code()).thenReturn(0)
        whenever(mockRetrofit.responseBodyConverter<Error>(any(), any())).thenReturn(mockConverter)
        whenever(mockResponse.errorBody()).thenReturn(mockResponseBody)
        whenever(mockResponseBody.string()).thenReturn(mockJson)
        whenever(mockConverter.convert(mockResponseBody)).thenReturn(null)

        mockRetrofit.observeBody(mockResponse)
                .subscribe({
                    nextCalls++
                }, { e ->
                    assertThat(StandardErrors(mockContext).toError(e)).isEqualTo(mockErrorUnknown)
                    errorCalled = true
                }, {
                    successCalled = true
                })

        assertThat(nextCalls).isEqualTo(0)
        assertThat(errorCalled).isTrue()
        assertThat(successCalled).isFalse()

        verify(mockResponse).code()
        verify(mockResponse).errorBody()
        verifyNoMoreInteractions(mockResponse)
        verify(mockConverter).convert(mockResponseBody)
        verifyNoMoreInteractions(mockConverter)
    }

}
