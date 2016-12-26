package ademar.study.template.presenter.home

import ademar.study.template.core.ext.asError
import ademar.study.template.core.interactor.GetHelloWorldUseCase
import ademar.study.template.injection.LifeCycleScope
import ademar.study.template.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@LifeCycleScope
class HomePresenter @Inject constructor(

        private val getHelloWorldUseCase: GetHelloWorldUseCase

) : BasePresenter<HomeView>() {

    fun onStart() {
        loadData()
    }

    fun onReloadClick() {
        loadData()
    }

    private fun loadData() {
        view?.showLoading()
        subscriptions.add(getHelloWorldUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ helloWorld ->
                    view?.bindHelloWorld(helloWorld)
                    view?.showContent()
                }, { e ->
                    view?.showError(e.asError())
                    view?.showRetry()
                }))
    }

}
