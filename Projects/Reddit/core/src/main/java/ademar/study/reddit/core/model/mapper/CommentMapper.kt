package ademar.study.reddit.core.model.mapper

import ademar.study.reddit.core.model.Comment
import javax.inject.Inject
import ademar.study.reddit.core.model.internal.Comment as InternalComment

class CommentMapper @Inject constructor() {

    fun transform(comment: InternalComment, level: Int = 0): Comment {
        val author = comment.author
        val text = comment.text
        val downs = comment.downs
        val ups = comment.ups
        val replies = arrayListOf<Comment>()
        comment.replies?.data?.children?.let {
            replies.addAll(it
                    .filter { it.isComment() }
                    .map { transform(it.comment, level + 1) })
        }
        return Comment(author, text, downs, ups, level, replies)
    }

}
