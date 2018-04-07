package ademar.study.template.presenter.detail

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.injection.LifeCycleScope
import ademar.study.template.mapper.DetailMapper
import ademar.study.template.mapper.ErrorMapper
import ademar.study.template.presenter.BasePresenter
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

    private fun loadData() {
        view?.showLoading()

        subscriptions.add(Observable.just<HelloWorld>(focused)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.bindDetail(detailMapper.transform(it, others))
                    view?.showContent()
                }, { e ->
                    view?.showError(errorMapper.transform(e))
                    view?.showRetry()
                }))
    }

}
