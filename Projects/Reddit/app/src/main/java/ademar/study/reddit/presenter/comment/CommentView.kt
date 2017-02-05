package ademar.study.reddit.presenter.comment

import ademar.study.reddit.model.comment.CommentViewModel
import ademar.study.reddit.presenter.LoadDataView

interface CommentView : LoadDataView {

    fun clearComments()

    fun bindComment(viewModel: CommentViewModel)

    fun showEmpty()

}
