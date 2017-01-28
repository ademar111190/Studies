package ademar.study.reddit.core.model

import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import com.bluelinelabs.logansquare.LoganSquare
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SubmissionTest : BaseTest() {

    @Test
    fun testParse() {
        val submission = LoganSquare.parse(Fixture.submission.JSON, Submission::class.java)
        assertThat(submission.title).isEqualTo(Fixture.submission.TITLE)
        assertThat(submission.author).isEqualTo(Fixture.submission.AUTHOR)
        assertThat(submission.thumbnail).isEqualTo(Fixture.submission.THUMBNAIL)
        assertThat(submission.created).isEqualTo(Fixture.submission.CREATED)
        assertThat(submission.comments).isEqualTo(Fixture.submission.COMMENTS)
        assertThat(submission.downs).isEqualTo(Fixture.submission.DOWNS)
        assertThat(submission.ups).isEqualTo(Fixture.submission.UPS)
    }

    @Test
    fun testSerialize() {
        val json = LoganSquare.serialize(Fixture.submission.makeModel())
        assertThat(json).contains("\"author\":\"${Fixture.submission.AUTHOR}\"")
        assertThat(json).contains("\"title\":\"${Fixture.submission.TITLE}\"")
        assertThat(json).contains("\"thumbnail\":\"${Fixture.submission.THUMBNAIL}\"")
        assertThat(json).contains("\"downs\":${Fixture.submission.DOWNS}")
        assertThat(json).contains("\"ups\":${Fixture.submission.UPS}")
        assertThat(json).contains("\"num_comments\":${Fixture.submission.COMMENTS}")
        assertThat(json).contains("\"created_utc\":${Fixture.submission.CREATED}")
    }

}
