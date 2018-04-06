package ademar.study.template.presenter.detail

import ademar.study.template.core.interactor.GetHelloWorld
import ademar.study.template.injection.LifeCycleScope
import ademar.study.template.mapper.ErrorMapper
import ademar.study.template.mapper.HelloWorldMapper
import ademar.study.template.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@LifeCycleScope
class DetailPresenter @Inject constructor(

        private val getHelloWorldUseCase: GetHelloWorld,
        private val helloWorldMapper: HelloWorldMapper,
        private val errorMapper: ErrorMapper

) : BasePresenter<DetailView>() {

    fun onStart() = loadData()

    fun onReloadClick() = loadData()

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
