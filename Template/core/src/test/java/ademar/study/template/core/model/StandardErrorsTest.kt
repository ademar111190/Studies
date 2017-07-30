package ademar.study.template.core.model

import ademar.study.template.core.test.BaseTest
import ademar.study.template.core.test.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.net.UnknownHostException

class StandardErrorsTest : BaseTest() {

    @Test
    fun testDefaults() {
        val standardErrors = StandardErrors(mockContext)
        val errors = listOf(
                standardErrors.UNKNOWN,
                standardErrors.UNAUTHORIZED,
                standardErrors.NO_CONNECTION)
        val errorsSet = hashSetOf(
                standardErrors.UNKNOWN,
                standardErrors.UNAUTHORIZED,
                standardErrors.NO_CONNECTION)
        assertThat(errors.size).isEqualTo(errorsSet.size)
        assertThat(StandardErrors(mockContext).UNKNOWN).isEqualTo(StandardErrors(mockContext).UNKNOWN)
        assertThat(StandardErrors(mockContext).UNAUTHORIZED).isEqualTo(StandardErrors(mockContext).UNAUTHORIZED)
        assertThat(StandardErrors(mockContext).NO_CONNECTION).isEqualTo(StandardErrors(mockContext).NO_CONNECTION)
    }

    @Test
    fun testProperties() {
        val standardErrors = StandardErrors(mockContext)
        val errors = listOf(
                standardErrors.UNKNOWN,
                standardErrors.UNAUTHORIZED,
                standardErrors.NO_CONNECTION)
        for ((i, a) in errors.withIndex()) {
            for ((j, b) in errors.withIndex()) {
                if (i == j) {
                    assertThat(a.code).isEqualTo(b.code)
                    assertThat(a.message).isEqualTo(b.message)
                } else {
                    assertThat(a.code).isNotEqualTo(b.code)
                    assertThat(a.message).isNotEqualTo(b.message)
                }
            }
        }
    }

    @Test
    fun testToError() {
        val standardErrors = StandardErrors(mockContext)
        val error = standardErrors.toError(Fixture.error.makeException())
        assertThat(error.code).isEqualTo(0)
        assertThat(error.message).isEqualTo(Fixture.error.MESSAGE)
    }

    @Test
    fun testToError_withCode() {
        val standardErrors = StandardErrors(mockContext)
        val exception = Fixture.error.makeModel()
        val mockErrorCode = 7
        exception.code = mockErrorCode
        val error = standardErrors.toError(exception)
        assertThat(error.code).isEqualTo(mockErrorCode)
        assertThat(error.message).isEqualTo(Fixture.error.MESSAGE)
    }

    @Test
    fun testToError_unknownHostException() {
        val standardErrors = StandardErrors(mockContext)
        val error = standardErrors.toError(UnknownHostException("Mock error"))
        assertThat(error.code).isEqualTo(10_003)
        assertThat(error.message).isEqualTo("NO_CONNECTION")
    }

}
