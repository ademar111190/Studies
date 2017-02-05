package ademar.study.reddit.core.model.internal

import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostDetailDataReplyTest : BaseTest() {

    @Test
    fun testParse() {
        val postDetailDataReply = LoganSquare.parse(Fixture.postDetailDataReply.JSON, PostDetailDataReply::class.java)
        assertThat(postDetailDataReply.data).isNotNull()
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.postDetailDataReply.makeModel())
        assertThat(json).contains("\"data\":{")
    }

}
