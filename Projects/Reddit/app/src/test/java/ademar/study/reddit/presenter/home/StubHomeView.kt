package ademar.study.reddit.presenter.home

import ademar.study.reddit.model.ErrorViewModel
import ademar.study.reddit.model.home.PostViewModel
import ademar.study.reddit.presenter.StubLoadDataView
import org.assertj.core.api.Assertions.fail

open class StubHomeView : StubLoadDataView(), HomeView {

    override fun clearPosts() {
        fail("Shouldn't call clearPosts")
    }

    override fun bindPost(viewModel: PostViewModel) {
        fail("Shouldn't call bindPost, viewModel $viewModel")
    }

    override fun showUnloadedPosts() {
        fail("Shouldn't call showUnloadedPosts")
    }

    override fun hideUnloadedPosts() {
        fail("Shouldn't call hideUnloadedPosts")
    }

    override fun showUnloadedError(viewModel: ErrorViewModel) {
        fail("Shouldn't call showUnloadedError, viewModel $viewModel")
    }

    override fun hideUnloadedError() {
        fail("Shouldn't call hideUnloadedError")
    }

}
