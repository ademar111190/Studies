package ademar.study.reddit.presenter.comment

import ademar.study.reddit.model.comment.CommentViewModel
import ademar.study.reddit.presenter.StubLoadDataView
import org.assertj.core.api.Assertions.fail

open class StubCommentView : StubLoadDataView(), CommentView {

    override fun clearComments() {
        fail("Shouldn't call clearComments")
    }

    override fun bindComment(viewModel: CommentViewModel) {
        fail("Shouldn't call bindComment, viewModel $viewModel")
    }

    override fun showEmpty() {
        fail("Shouldn't call showEmpty")
    }

}
