package ademar.study.reddit.mapper.comment

import ademar.study.reddit.R
import ademar.study.reddit.test.BaseTest
import ademar.study.reddit.test.Fixture
import ademar.study.reddit.view.base.BaseActivity
import android.content.res.Resources
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever

class CommentMapperTest : BaseTest() {

    @Mock lateinit var mockBaseActivity: BaseActivity
    @Mock lateinit var mockResources: Resources

    private val mockDowns = "mockDowns"
    private val mockUps = "mockUps"

    @Before
    override fun setUp() {
        super.setUp()
        whenever(mockBaseActivity.resources).thenReturn(mockResources)
        whenever(mockResources.getString(R.string.post_downs, Fixture.post.DOWNS)).thenReturn(mockDowns)
        whenever(mockResources.getString(R.string.post_ups, Fixture.post.UPS)).thenReturn(mockUps)
    }

    @Test
    fun testTransform() {
        val mapper = CommentMapper(mockBaseActivity)
        val viewModel = mapper.transform(Fixture.comment.makeModel())

        assertThat(viewModel.text).isEqualTo(Fixture.comment.TEXT)
        assertThat(viewModel.author).isEqualTo(Fixture.comment.AUTHOR)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

}
