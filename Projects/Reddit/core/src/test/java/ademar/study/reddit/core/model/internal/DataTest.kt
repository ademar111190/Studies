package ademar.study.reddit.core.model.internal

import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DataTest : BaseTest() {

    @Test
    fun testParse() {
        val data = LoganSquare.parse(Fixture.data.JSON, Data::class.java)
        assertThat(data.children.size).isEqualTo(1)
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.data.makeModel())
        assertThat(json).contains("\"children\":[{")
    }

}
