package ademar.study.reddit.core.model.internal

import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CommentTest : BaseTest() {

    @Test
    fun testParse() {
        val comment = LoganSquare.parse(Fixture.comment.JSON, Comment::class.java)
        assertThat(comment.author).isEqualTo(Fixture.comment.AUTHOR)
        assertThat(comment.text).isEqualTo(Fixture.comment.TEXT)
        assertThat(comment.downs).isEqualTo(Fixture.comment.DOWNS)
        assertThat(comment.ups).isEqualTo(Fixture.comment.UPS)
        assertThat(comment.replies).isNull()
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.comment.makeModel())
        assertThat(json).contains("\"author\":\"${Fixture.comment.AUTHOR}\"")
        assertThat(json).contains("\"body\":\"${Fixture.comment.TEXT}\"")
        assertThat(json).contains("\"downs\":${Fixture.comment.DOWNS}")
        assertThat(json).contains("\"ups\":${Fixture.comment.UPS}")
    }

}
