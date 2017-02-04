package ademar.study.reddit.core.model.internal

import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostDetailDataTest : BaseTest() {

    @Test
    fun testParse() {
        val postDetailData = LoganSquare.parse(Fixture.postDetailData.JSON, PostDetailData::class.java)
        assertThat(postDetailData.children.size).isEqualTo(1)
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.postDetailData.makeModel())
        assertThat(json).contains("\"children\":[{")
    }

}
