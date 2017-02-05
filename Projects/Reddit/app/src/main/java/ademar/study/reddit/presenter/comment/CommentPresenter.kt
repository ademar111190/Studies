package ademar.study.reddit.presenter.comment

import ademar.study.reddit.core.interactor.GetCommentsUseCase
import ademar.study.reddit.core.model.Comment
import ademar.study.reddit.injection.LifeCycleScope
import ademar.study.reddit.mapper.ErrorMapper
import ademar.study.reddit.mapper.comment.CommentMapper
import ademar.study.reddit.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@LifeCycleScope
class CommentPresenter @Inject constructor(

        private val getCommentsUseCase: GetCommentsUseCase,
        private val commentMapper: CommentMapper,
        private val errorMapper: ErrorMapper

) : BasePresenter<CommentView>() {

    private var link = ""

    fun onStart(link: String) {
        this.link = link
        loadData()
    }

    fun onReloadClick() {
        loadData()
    }

    private fun loadData() {
        view?.showLoading()
        view?.clearComments()
        link.let { link ->
            if (link.isEmpty()) {
                view?.showRetry()
            } else {
                var hasComment = false
                subscriptions.add(getCommentsUseCase.getComments(link)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ comment ->
                            hasComment = true
                            showComment(comment)
                            view?.showContent()
                        }, { e ->
                            view?.showError(errorMapper.transform(e))
                            view?.showRetry()
                        }, {
                            if (!hasComment) {
                                view?.showEmpty()
                            }
                        }))
            }
        }
    }

    private fun showComment(comment: Comment) {
        view?.bindComment(commentMapper.transform(comment))
        comment.comments.forEach { showComment(it) }
    }

}
