package ademar.study.reddit.core.model.internal

import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ChildTest : BaseTest() {

    @Test
    fun testParse() {
        val child = LoganSquare.parse(Fixture.child.JSON, Child::class.java)
        assertThat(child.submission).isNotNull()
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.child.makeModel())
        assertThat(json).contains("\"data\":{")
    }

}
