package ademar.study.reddit.core.model.mapper

import ademar.study.reddit.core.model.Comment
import javax.inject.Inject
import ademar.study.reddit.core.model.internal.Comment as InternalComment

class CommentMapper @Inject constructor() {

    fun transform(comment: InternalComment): Comment {
        val author = comment.author
        val text = comment.text
        val downs = comment.downs
        val ups = comment.ups
        val replies = arrayListOf<Comment>()
        comment.replies?.let {
            replies.addAll(it.children
                    .filter { it.isComment() }
                    .map { transform(it.comment) })
        }
        return Comment(author, text, downs, ups, replies)
    }

}
