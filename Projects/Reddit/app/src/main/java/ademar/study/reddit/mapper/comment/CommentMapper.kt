package ademar.study.reddit.mapper.comment

import ademar.study.reddit.R
import ademar.study.reddit.core.model.Comment
import ademar.study.reddit.model.comment.CommentViewModel
import ademar.study.reddit.view.base.BaseActivity
import javax.inject.Inject

class CommentMapper @Inject constructor(

        private val context: BaseActivity

) {

    fun transform(comment: Comment): CommentViewModel {
        val author = comment.author
        val text = comment.text

        val resources = context.resources
        val downs = resources.getString(R.string.post_downs, comment.downs)
        val ups = resources.getString(R.string.post_ups, comment.ups)

        val comments = comment.comments.map { transform(it) }

        return CommentViewModel(author, text, downs, ups, comments)
    }

}
