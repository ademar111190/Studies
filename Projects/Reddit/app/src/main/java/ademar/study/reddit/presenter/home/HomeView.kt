package ademar.study.reddit.presenter.home

import ademar.study.reddit.model.ErrorViewModel
import ademar.study.reddit.model.home.PostViewModel
import ademar.study.reddit.presenter.LoadDataView

interface HomeView : LoadDataView {

    fun clearPosts()

    fun bindPost(viewModel: PostViewModel)

    fun showUnloadedPosts()

    fun hideUnloadedPosts()

    fun showUnloadedError(viewModel: ErrorViewModel)

    fun hideUnloadedError()

}
