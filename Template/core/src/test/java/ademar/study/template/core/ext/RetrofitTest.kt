package ademar.study.template.core.ext

import ademar.study.template.core.model.Error
import ademar.study.template.core.test.BaseTest
import ademar.study.template.core.test.Fixture
import okhttp3.ResponseBody
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import org.mockito.Mockito.`when` as whenever

class RetrofitTest : BaseTest() {

    @Mock lateinit var mockRetrofit: Retrofit
    @Mock lateinit var mockResponse: Response<String>
    @Mock lateinit var mockResponseBody: ResponseBody
    @Mock lateinit var mockConverter: Converter<ResponseBody, Error>
    @Mock lateinit var mockOnNext: (String) -> Unit
    @Mock lateinit var mockOnError: (Throwable) -> Unit
    @Mock lateinit var mockOnSuccess: () -> Unit

    @Test
    fun testObserveBody_200() {
        val foo = "Foo"
        whenever(mockResponse.code()).thenReturn(200)
        whenever(mockResponse.body()).thenReturn(foo)

        mockRetrofit.observeBody(mockResponse)
                .subscribe(mockOnNext, mockOnError, mockOnSuccess)

        verify(mockResponse).code()
        verify(mockResponse).body()
        verifyNoMoreInteractions(mockResponse)
        verify(mockOnNext).invoke(foo)
        verifyNoMoreInteractions(mockOnNext)
        verifyZeroInteractions(mockOnError)
        verify(mockOnSuccess).invoke()
        verifyNoMoreInteractions(mockOnSuccess)
    }

    @Test
    fun testObserveBody_401() {
        whenever(mockResponse.code()).thenReturn(401)

        mockRetrofit.observeBody(mockResponse)
                .subscribe(mockOnNext, mockOnError, mockOnSuccess)

        verify(mockResponse).code()
        verifyNoMoreInteractions(mockResponse)
        verifyZeroInteractions(mockOnNext)
        verify(mockOnError).invoke(Error.UNAUTHORIZED)
        verifyZeroInteractions(mockOnSuccess)
    }

    @Test
    fun testObserveBody_error() {
        val errorModel = Fixture.error.makeModel()
        whenever(mockResponse.code()).thenReturn(0)
        whenever(mockRetrofit.responseBodyConverter<Error>(any(), any())).thenReturn(mockConverter)
        whenever(mockResponse.errorBody()).thenReturn(mockResponseBody)
        whenever(mockConverter.convert(mockResponseBody)).thenReturn(errorModel)

        mockRetrofit.observeBody(mockResponse)
                .subscribe(mockOnNext, mockOnError, mockOnSuccess)

        verify(mockResponse).code()
        verify(mockResponse).errorBody()
        verifyNoMoreInteractions(mockResponse)
        verifyZeroInteractions(mockResponseBody)
        verify(mockConverter).convert(mockResponseBody)
        verifyNoMoreInteractions(mockConverter)
        verifyNoMoreInteractions(mockOnNext)
        verify(mockOnError).invoke(errorModel)
        verifyNoMoreInteractions(mockOnError)
        verifyZeroInteractions(mockOnSuccess)
    }

    @Test
    fun testObserveBody_exception() {
        whenever(mockResponse.code()).thenReturn(0)
        whenever(mockRetrofit.responseBodyConverter<Error>(any(), any())).thenReturn(null)

        mockRetrofit.observeBody(mockResponse)
                .subscribe(mockOnNext, mockOnError, mockOnSuccess)

        verify(mockResponse).code()
        verify(mockResponse).errorBody()
        verifyNoMoreInteractions(mockResponse)
        verifyZeroInteractions(mockOnNext)
        verifyZeroInteractions(mockOnSuccess)
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
                .subscribe(mockOnNext, mockOnError, mockOnSuccess)

        verify(mockResponse).code()
        verify(mockResponse).errorBody()
        verifyNoMoreInteractions(mockResponse)
        verify(mockConverter).convert(mockResponseBody)
        verifyNoMoreInteractions(mockConverter)
        verifyZeroInteractions(mockOnNext)
        verifyZeroInteractions(mockOnSuccess)
    }

}
