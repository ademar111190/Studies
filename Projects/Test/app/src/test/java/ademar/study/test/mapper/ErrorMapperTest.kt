package ademar.study.test.mapper

import ademar.study.test.test.BaseTest
import ademar.study.test.test.Fixture.MESSAGE
import ademar.study.test.test.Fixture.NO_CONNECTION
import ademar.study.test.test.Fixture.UNAUTHORIZED
import ademar.study.test.test.Fixture.UNKNOWN
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mock
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

class ErrorMapperTest : BaseTest() {

    @Mock lateinit var mockResponse: Response<Any>

    @Test
    fun testTransform_null() {
        val mapper = ErrorMapper(mockContext)
        val viewModel = mapper.transform(null)
        assertThat(viewModel.message).isEqualTo(UNKNOWN)
    }

    @Test
    fun testTransform_noConnection() {
        val mapper = ErrorMapper(mockContext)
        val viewModel = mapper.transform(UnknownHostException())
        assertThat(viewModel.message).isEqualTo(NO_CONNECTION)
    }

    @Test
    fun testTransform_401() {
        whenever(mockResponse.code()).thenReturn(401)
        val mapper = ErrorMapper(mockContext)
        val viewModel = mapper.transform(HttpException(mockResponse))
        assertThat(viewModel.message).isEqualTo(UNAUTHORIZED)
    }

    @Test
    fun testTransform_otherCode() {
        whenever(mockResponse.message()).thenReturn(MESSAGE)
        val mapper = ErrorMapper(mockContext)
        val viewModel = mapper.transform(HttpException(mockResponse))
        assertThat(viewModel.message).isEqualTo(MESSAGE)
    }

    @Test
    fun testTransform_otherCode_noMessage() {
        val mapper = ErrorMapper(mockContext)
        val viewModel = mapper.transform(HttpException(mockResponse))
        assertThat(viewModel.message).isEqualTo(UNKNOWN)
    }

    @Test
    fun testTransform_otherError() {
        val mapper = ErrorMapper(mockContext)
        val viewModel = mapper.transform(Error(MESSAGE))
        assertThat(viewModel.message).isEqualTo(MESSAGE)
    }

    @Test
    fun testTransform_otherError_noMessage() {
        val mapper = ErrorMapper(mockContext)
        val viewModel = mapper.transform(Error())
        assertThat(viewModel.message).isEqualTo(UNKNOWN)
    }

}
