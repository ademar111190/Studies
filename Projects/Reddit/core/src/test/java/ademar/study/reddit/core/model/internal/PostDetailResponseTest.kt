package ademar.study.reddit.core.model.internal

import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostDetailResponseTest : BaseTest() {

    @Test
    fun testParse() {
        val postDetailResponse = LoganSquare.parse(Fixture.postDetailResponse.JSON, PostDetailResponse::class.java)
        assertThat(postDetailResponse.data).isNotNull()
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.postDetailResponse.makeModel())
        assertThat(json).contains("\"data\":{")
    }

}
