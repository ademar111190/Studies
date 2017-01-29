package ademar.study.reddit.mapper.home

import ademar.study.reddit.test.BaseTest
import ademar.study.reddit.test.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class PostMapperTest : BaseTest() {

    @Test
    fun testTransform() {
        val mapper = PostMapper()
        val viewModel = mapper.transform(Fixture.post.makeModel())
        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(Fixture.post.CREATED)
        assertThat(viewModel.comments).isEqualTo(Fixture.post.COMMENTS)
        assertThat(viewModel.downs).isEqualTo(Fixture.post.DOWNS)
    }

}
