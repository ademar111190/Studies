package ademar.study.reddit.core.model.internal

import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostDetailDataChildrenTest : BaseTest() {

    @Test
    fun testParse() {
        val postDetailDataChildren = LoganSquare.parse(Fixture.postDetailDataChildren.JSON, PostDetailDataChildren::class.java)
        assertThat(postDetailDataChildren.kind).isEqualTo(Fixture.postDetailDataChildren.KIND)
        assertThat(postDetailDataChildren.comment).isNotNull()
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.postDetailDataChildren.makeModel())
        assertThat(json).contains("\"kind\":\"${Fixture.postDetailDataChildren.KIND}\"")
        assertThat(json).contains("\"data\":{")
    }

    @Test
    fun testIsComment() {
        assertThat(PostDetailDataChildren().apply {
            kind = "t1"
        }.isComment()).isTrue()

        assertThat(PostDetailDataChildren().apply {
            kind = "anything"
        }.isComment()).isFalse()
    }

}
