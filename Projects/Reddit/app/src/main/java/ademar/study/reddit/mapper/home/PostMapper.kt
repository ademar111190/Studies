package ademar.study.reddit.mapper.home

import ademar.study.reddit.R
import ademar.study.reddit.core.model.Post
import ademar.study.reddit.model.home.PostViewModel
import ademar.study.reddit.plataform.factories.CalendarFactory
import ademar.study.reddit.view.base.BaseActivity
import java.util.*
import java.util.Calendar.*
import javax.inject.Inject

class PostMapper @Inject constructor(

        private val context: BaseActivity,
        private val factory: CalendarFactory

) {

    fun transform(post: Post): PostViewModel {
        val title = post.title
        val author = post.author
        val thumbnail = post.thumbnail

        val create = factory.makeCalendar()
        create.time = Date(post.created * 1000L)
        val now = factory.makeCalendar()

        val yearDiff = now.get(YEAR) - create.get(YEAR)
        val monthDiff = now.get(MONTH) - create.get(MONTH)
        val dayDiff = now.get(DAY_OF_MONTH) - create.get(DAY_OF_MONTH)
        val hourDiff = now.get(HOUR) - create.get(HOUR)
        val minuteDiff = now.get(MINUTE) - create.get(MINUTE)
        val secondDiff = now.get(SECOND) - create.get(SECOND)

        val resources = context.resources
        val created = if (yearDiff > 0) {
            resources.getQuantityString(R.plurals.post_time_year, yearDiff, yearDiff)
        } else if (monthDiff > 0) {
            resources.getQuantityString(R.plurals.post_time_month, monthDiff, monthDiff)
        } else if (dayDiff > 0) {
            resources.getQuantityString(R.plurals.post_time_day, dayDiff, dayDiff)
        } else if (hourDiff > 0) {
            resources.getQuantityString(R.plurals.post_time_hour, hourDiff, hourDiff)
        } else if (minuteDiff > 0) {
            resources.getQuantityString(R.plurals.post_time_minute, minuteDiff, minuteDiff)
        } else if (secondDiff > 0) {
            resources.getQuantityString(R.plurals.post_time_second, secondDiff, secondDiff)
        } else {
            resources.getString(R.string.post_time_now)
        }

        val comments = resources.getQuantityString(R.plurals.post_comments, if (post.comments > Int.MAX_VALUE) {
            Int.MAX_VALUE
        } else if (post.comments < Int.MIN_VALUE) {
            Int.MIN_VALUE
        } else {
            post.comments.toInt()
        }, post.comments)

        val downs = resources.getString(R.string.post_downs, post.downs)
        val ups = resources.getString(R.string.post_ups, post.ups)

        return PostViewModel(title, author, thumbnail, created, comments, downs, ups)
    }

}
