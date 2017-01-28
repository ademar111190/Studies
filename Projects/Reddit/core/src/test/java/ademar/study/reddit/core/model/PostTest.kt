package ademar.study.reddit.core.model

import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostTest : BaseTest() {

    @Test
    fun testParse() {
        val post = LoganSquare.parse(Fixture.post.JSON, Post::class.java)
        assertThat(post.title).isEqualTo(Fixture.post.TITLE)
        assertThat(post.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(post.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(post.created).isEqualTo(Fixture.post.CREATED)
        assertThat(post.comments).isEqualTo(Fixture.post.COMMENTS)
        assertThat(post.downs).isEqualTo(Fixture.post.DOWNS)
        assertThat(post.ups).isEqualTo(Fixture.post.UPS)
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.post.makeModel())
        assertThat(json).contains("\"author\":\"${Fixture.post.AUTHOR}\"")
        assertThat(json).contains("\"title\":\"${Fixture.post.TITLE}\"")
        assertThat(json).contains("\"thumbnail\":\"${Fixture.post.THUMBNAIL}\"")
        assertThat(json).contains("\"downs\":${Fixture.post.DOWNS}")
        assertThat(json).contains("\"ups\":${Fixture.post.UPS}")
        assertThat(json).contains("\"num_comments\":${Fixture.post.COMMENTS}")
        assertThat(json).contains("\"created_utc\":${Fixture.post.CREATED}")
    }

}
