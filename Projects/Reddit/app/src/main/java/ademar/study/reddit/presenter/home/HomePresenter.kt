package ademar.study.reddit.presenter.home

import ademar.study.reddit.core.interactor.GetHelloWorldUseCase
import ademar.study.reddit.injection.LifeCycleScope
import ademar.study.reddit.mapper.ErrorMapper
import ademar.study.reddit.mapper.HelloWorldMapper
import ademar.study.reddit.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@LifeCycleScope
class HomePresenter @Inject constructor(

        private val getHelloWorldUseCase: GetHelloWorldUseCase,
        private val helloWorldMapper: HelloWorldMapper,
        private val errorMapper: ErrorMapper

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
                    view?.bindHelloWorld(helloWorldMapper.transform(helloWorld))
                    view?.showContent()
                }, { e ->
                    view?.showError(errorMapper.transform(e))
                    view?.showRetry()
                }))
    }

}
