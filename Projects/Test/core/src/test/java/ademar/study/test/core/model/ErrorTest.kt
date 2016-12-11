package ademar.study.test.core.model

import ademar.study.test.core.test.BaseTest
import ademar.study.test.core.test.Fixture
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ErrorTest : BaseTest() {

    @Test
    fun testParse() {
        val error = LoganSquare.parse(Fixture.error.JSON, Error::class.java)
        assertThat(error.code).isEqualTo(Fixture.error.CODE)
        assertThat(error.message).isEqualTo(Fixture.error.MESSAGE)
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.error.makeModel())
        assertThat(json).contains("\"code\":${Fixture.error.CODE}")
        assertThat(json).contains("\"message\":\"${Fixture.error.MESSAGE}\"")
    }

    @Test
    fun testReport() {
        val error = Error()
        error.report()
    }

    @Test
    fun testDefaults() {
        val errors = arrayListOf(
                Error.UNKNOWN,
                Error.UNAUTHORIZED
        )
        val errorsSet = hashSetOf(
                Error.UNKNOWN,
                Error.UNAUTHORIZED
        )
        assertThat(errors.size).isEqualTo(errorsSet.size)

        assertThat(Error.UNKNOWN).isEqualTo(Error.UNKNOWN)
        assertThat(Error.UNAUTHORIZED).isEqualTo(Error.UNAUTHORIZED)
    }

}
