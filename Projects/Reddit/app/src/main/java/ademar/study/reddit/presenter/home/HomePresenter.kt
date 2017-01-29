package ademar.study.reddit.presenter.home

import ademar.study.reddit.core.interactor.GetPostsUseCase
import ademar.study.reddit.injection.LifeCycleScope
import ademar.study.reddit.mapper.ErrorMapper
import ademar.study.reddit.mapper.home.PostMapper
import ademar.study.reddit.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@LifeCycleScope
class HomePresenter @Inject constructor(

        private val getPostsUseCase: GetPostsUseCase,
        private val postMapper: PostMapper,
        private val errorMapper: ErrorMapper

) : BasePresenter<HomeView>() {

    fun onStart() {
        loadData()
    }

    fun onReloadClick() {
        loadData()
    }

    fun onPreviousPageClick() {
        view?.hideUnloadedError()
        view?.showUnloadedPosts()
        subscriptions.add(getPostsUseCase.previousPage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ post ->
                    view?.bindPost(postMapper.transform(post))
                }, { e ->
                    view?.showUnloadedError(errorMapper.transform(e))
                }))
    }

    private fun loadData() {
        view?.showLoading()
        view?.clearPosts()
        subscriptions.add(getPostsUseCase.currentPage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ post ->
                    view?.bindPost(postMapper.transform(post))
                    view?.showContent()
                }, { e ->
                    view?.showError(errorMapper.transform(e))
                    view?.showRetry()
                }))
    }

}
