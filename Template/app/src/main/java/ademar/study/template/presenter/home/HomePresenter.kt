package ademar.study.template.presenter.home

import ademar.study.template.core.interactor.GetAllHelloWorldUseCase
import ademar.study.template.injection.LifeCycleScope
import ademar.study.template.mapper.ErrorMapper
import ademar.study.template.mapper.HelloWorldMapper
import ademar.study.template.navigation.FlowController
import ademar.study.template.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@LifeCycleScope
class HomePresenter @Inject constructor(

        private val flowController: FlowController,
        private val getAllHelloWorldUseCase: GetAllHelloWorldUseCase,
        private val helloWorldMapper: HelloWorldMapper,
        private val errorMapper: ErrorMapper

) : BasePresenter<HomeView>() {

    fun onStart() {
        loadData()
    }

    fun onReloadClick() {
        loadData()
    }

    fun onHelloWorldClick() {
        flowController.launchDetail()
    }

    private fun loadData() {
        view?.showLoading()
        view?.clearHelloWorlds()
        subscriptions.add(getAllHelloWorldUseCase.execute()
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
