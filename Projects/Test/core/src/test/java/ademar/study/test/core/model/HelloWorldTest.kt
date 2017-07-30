package ademar.study.test.core.model

import ademar.study.test.core.test.BaseTest
import ademar.study.test.core.test.Fixture
import ademar.study.test.core.test.JsonAssertions.assertJsonStringValue
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HelloWorldTest : BaseTest() {

    @Test
    fun testParse() {
        val helloWorld = LoganSquare.parse(Fixture.helloWorld.JSON, HelloWorld::class.java)
        assertThat(helloWorld.message).isEqualTo(Fixture.helloWorld.MESSAGE)
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.helloWorld.makeModel())
        assertJsonStringValue(json, "message", Fixture.helloWorld.MESSAGE)
    }

}
