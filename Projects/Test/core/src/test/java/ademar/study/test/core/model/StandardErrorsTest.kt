package ademar.study.test.core.model

import ademar.study.test.core.test.BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class StandardErrorsTest : BaseTest() {

    @Test
    fun testDefaults() {
        val standardErrors = StandardErrors(mockContext)
        val errors = listOf(
                standardErrors.UNKNOWN,
                standardErrors.UNAUTHORIZED)
        val errorsSet = hashSetOf(
                standardErrors.UNKNOWN,
                standardErrors.UNAUTHORIZED)
        assertThat(errors.size).isEqualTo(errorsSet.size)
        assertThat(StandardErrors(mockContext).UNKNOWN).isEqualTo(StandardErrors(mockContext).UNKNOWN)
        assertThat(StandardErrors(mockContext).UNAUTHORIZED).isEqualTo(StandardErrors(mockContext).UNAUTHORIZED)
    }

}
