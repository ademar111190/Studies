package ademar.study.test.core.ext

import ademar.study.test.core.test.BaseTest
import ademar.study.test.core.test.Fixture
import org.assertj.core.api.Assertions
import org.junit.Test

class ThrowableTest : BaseTest() {

    @Test
    fun testAsError() {
        val error = Fixture.error.makeException().asError()
        Assertions.assertThat(error.code).isEqualTo(0)
        Assertions.assertThat(error.message).isEqualTo(Fixture.error.MESSAGE)
    }

    @Test
    fun testAsError_withCode() {
        val exception = Fixture.error.makeModel()
        val mockErrorCode = 7
        exception.code = mockErrorCode
        val error = exception.asError()
        Assertions.assertThat(error.code).isEqualTo(mockErrorCode)
        Assertions.assertThat(error.message).isEqualTo(Fixture.error.MESSAGE)
    }

}
