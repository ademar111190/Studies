package ademar.study.template.core.model

import ademar.study.template.core.test.BaseTest
import ademar.study.template.core.test.Fixture
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
        assertThat(json).contains("\"message\":\"${Fixture.helloWorld.MESSAGE}\"")
    }

}
