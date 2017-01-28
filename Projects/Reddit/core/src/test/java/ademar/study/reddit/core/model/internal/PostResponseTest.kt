package ademar.study.reddit.core.model.internal

import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostResponseTest : BaseTest() {

    @Test
    fun testParse() {
        val response = LoganSquare.parse(Fixture.postResponse.JSON, PostResponse::class.java)
        assertThat(response.data).isNotNull()
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.postResponse.makeModel())
        assertThat(json).contains("\"data\":{")
    }

}
