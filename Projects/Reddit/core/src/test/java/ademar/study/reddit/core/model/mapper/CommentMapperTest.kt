package ademar.study.reddit.core.model.mapper

import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CommentMapperTest : BaseTest() {

    @Test
    fun testTransform() {
        val mapper = CommentMapper()
        val comment = mapper.transform(Fixture.comment.makeModel())
        assertThat(comment.author).isEqualTo(Fixture.comment.AUTHOR)
        assertThat(comment.text).isEqualTo(Fixture.comment.TEXT)
        assertThat(comment.downs).isEqualTo(Fixture.comment.DOWNS)
        assertThat(comment.ups).isEqualTo(Fixture.comment.UPS)
        assertThat(comment.comments.size).isEqualTo(0)
    }

    @Test
    fun testTransform_withChildren() {
        val mapper = CommentMapper()
        val comment = mapper.transform(Fixture.comment.makeModel().apply {
            replies = Fixture.postDetailData.makeModel()
        })
        assertThat(comment.author).isEqualTo(Fixture.comment.AUTHOR)
        assertThat(comment.text).isEqualTo(Fixture.comment.TEXT)
        assertThat(comment.downs).isEqualTo(Fixture.comment.DOWNS)
        assertThat(comment.ups).isEqualTo(Fixture.comment.UPS)
        assertThat(comment.comments.size).isEqualTo(1)
    }

}
