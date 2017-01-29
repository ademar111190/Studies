package ademar.study.reddit.mapper.home

import ademar.study.reddit.R
import ademar.study.reddit.plataform.factories.CalendarFactory
import ademar.study.reddit.test.BaseTest
import ademar.study.reddit.test.Fixture
import ademar.study.reddit.view.base.BaseActivity
import android.content.res.Resources
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import java.util.*
import java.util.Calendar.*
import org.mockito.Mockito.`when` as whenever

class PostMapperTest : BaseTest() {

    @Mock lateinit var mockBaseActivity: BaseActivity
    @Mock lateinit var mockResources: Resources
    @Mock lateinit var mockCalendarFactory: CalendarFactory
    @Mock lateinit var mockNow: Calendar
    @Mock lateinit var mockCreated: Calendar

    private val mockNowText = "mockNowText"
    private val mockYear = "mockYear"
    private val mockYears = "mockYears"
    private val mockMonth = "mockMonth"
    private val mockMonths = "mockMonths"
    private val mockDay = "mockDay"
    private val mockDays = "mockDays"
    private val mockHour = "mockHour"
    private val mockHours = "mockHours"
    private val mockMinute = "mockMinute"
    private val mockMinutes = "mockMinutes"
    private val mockSecond = "mockSecond"
    private val mockSeconds = "mockSeconds"
    private val mockEmptyComment = "mockEmptyComment"
    private val mockComment = "mockComment"
    private val mockComments = "mockComments"
    private val mockDowns = "mockDowns"
    private val mockUps = "mockUps"

    @Before
    override fun setUp() {
        super.setUp()
        whenever(mockCalendarFactory.makeCalendar()).thenReturn(mockCreated, mockNow)
        whenever(mockBaseActivity.resources).thenReturn(mockResources)
        whenever(mockResources.getQuantityString(R.plurals.post_comments, 0, 0L)).thenReturn(mockEmptyComment)
        whenever(mockResources.getQuantityString(R.plurals.post_comments, 1, 1L)).thenReturn(mockComment)
        whenever(mockResources.getQuantityString(R.plurals.post_comments, Fixture.post.COMMENTS.toInt(), Fixture.post.COMMENTS)).thenReturn(mockComments)
        whenever(mockResources.getString(R.string.post_downs, Fixture.post.DOWNS)).thenReturn(mockDowns)
        whenever(mockResources.getString(R.string.post_ups, Fixture.post.UPS)).thenReturn(mockUps)
        whenever(mockResources.getString(R.string.post_time_now)).thenReturn(mockNowText)
        whenever(mockResources.getQuantityString(R.plurals.post_time_year, 1, 1)).thenReturn(mockYear)
        whenever(mockResources.getQuantityString(R.plurals.post_time_year, 2, 2)).thenReturn(mockYears)
        whenever(mockResources.getQuantityString(R.plurals.post_time_month, 1, 1)).thenReturn(mockMonth)
        whenever(mockResources.getQuantityString(R.plurals.post_time_month, 2, 2)).thenReturn(mockMonths)
        whenever(mockResources.getQuantityString(R.plurals.post_time_day, 1, 1)).thenReturn(mockDay)
        whenever(mockResources.getQuantityString(R.plurals.post_time_day, 2, 2)).thenReturn(mockDays)
        whenever(mockResources.getQuantityString(R.plurals.post_time_hour, 1, 1)).thenReturn(mockHour)
        whenever(mockResources.getQuantityString(R.plurals.post_time_hour, 2, 2)).thenReturn(mockHours)
        whenever(mockResources.getQuantityString(R.plurals.post_time_minute, 1, 1)).thenReturn(mockMinute)
        whenever(mockResources.getQuantityString(R.plurals.post_time_minute, 2, 2)).thenReturn(mockMinutes)
        whenever(mockResources.getQuantityString(R.plurals.post_time_second, 1, 1)).thenReturn(mockSecond)
        whenever(mockResources.getQuantityString(R.plurals.post_time_second, 2, 2)).thenReturn(mockSeconds)
    }

    @Test
    fun testTransform_noComments() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel().apply {
            comments = 0L
        })

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockNowText)
        assertThat(viewModel.comments).isEqualTo(mockEmptyComment)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_singleComment() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel().apply {
            comments = 1L
        })

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockNowText)
        assertThat(viewModel.comments).isEqualTo(mockComment)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_now() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockNowText)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_second() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(2)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockSecond)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_seconds() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(3)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockSeconds)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_minute() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(2)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockMinute)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_minutes() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(3)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockMinutes)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_hour() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(2)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockHour)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_hours() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(3)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockHours)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_day() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(2)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockDay)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_days() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(3)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockDays)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_month() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(2)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockMonth)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_months() {
        whenever(mockNow.get(YEAR)).thenReturn(1)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(3)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockMonths)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_year() {
        whenever(mockNow.get(YEAR)).thenReturn(2)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockYear)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

    @Test
    fun testTransform_years() {
        whenever(mockNow.get(YEAR)).thenReturn(3)
        whenever(mockCreated.get(YEAR)).thenReturn(1)
        whenever(mockNow.get(MONTH)).thenReturn(1)
        whenever(mockCreated.get(MONTH)).thenReturn(1)
        whenever(mockNow.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockCreated.get(DAY_OF_MONTH)).thenReturn(1)
        whenever(mockNow.get(HOUR)).thenReturn(1)
        whenever(mockCreated.get(HOUR)).thenReturn(1)
        whenever(mockNow.get(MINUTE)).thenReturn(1)
        whenever(mockCreated.get(MINUTE)).thenReturn(1)
        whenever(mockNow.get(SECOND)).thenReturn(1)
        whenever(mockCreated.get(SECOND)).thenReturn(1)

        val mapper = PostMapper(mockBaseActivity, mockCalendarFactory)
        val viewModel = mapper.transform(Fixture.post.makeModel())

        assertThat(viewModel.title).isEqualTo(Fixture.post.TITLE)
        assertThat(viewModel.author).isEqualTo(Fixture.post.AUTHOR)
        assertThat(viewModel.thumbnail).isEqualTo(Fixture.post.THUMBNAIL)
        assertThat(viewModel.created).isEqualTo(mockYears)
        assertThat(viewModel.comments).isEqualTo(mockComments)
        assertThat(viewModel.downs).isEqualTo(mockDowns)
        assertThat(viewModel.ups).isEqualTo(mockUps)
    }

}
