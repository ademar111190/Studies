package ademar.study.template.core.model

import ademar.study.template.core.test.BaseTest
import ademar.study.template.core.test.Fixture
import ademar.study.template.core.test.JsonAssertions.assertJsonIntValue
import ademar.study.template.core.test.JsonAssertions.assertJsonStringValue
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
        assertJsonIntValue(json, "code", Fixture.error.CODE)
        assertJsonStringValue(json, "message", Fixture.error.MESSAGE)
    }

    @Test
    fun testReport() {
        val error = Error()
        error.report()
    }

}
