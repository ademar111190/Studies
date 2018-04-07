package ademar.study.test.presenter.detail

import ademar.study.test.core.model.HelloWorld
import ademar.study.test.injection.LifeCycleScope
import ademar.study.test.mapper.DetailMapper
import ademar.study.test.mapper.ErrorMapper
import ademar.study.test.model.HelloWorldViewModel
import ademar.study.test.presenter.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

@LifeCycleScope
class DetailPresenter @Inject constructor(

        private val detailMapper: DetailMapper,
        private val errorMapper: ErrorMapper,
        @Named("focused") private val focused: HelloWorld,
        @Named("others") private val others: List<HelloWorld>

) : BasePresenter<DetailView>() {

    fun onStart() = loadData()

    fun itemFocused(viewModel: HelloWorldViewModel) {
        view?.bindFocus(viewModel)
    }

    private fun loadData() {
        view?.showLoading()

        subscriptions.add(Observable.just<HelloWorld>(focused)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val viewModel = detailMapper.transform(it, others)
                    view?.bindDetail(viewModel)
                    view?.bindFocus(viewModel.focused)
                    view?.showContent()
                }, { e ->
                    view?.showError(errorMapper.transform(e))
                    view?.showRetry()
                }))
    }

}
