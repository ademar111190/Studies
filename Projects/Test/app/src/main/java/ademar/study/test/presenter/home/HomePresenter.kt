package ademar.study.test.presenter.home

import ademar.study.test.core.interactor.GetHelloWorlds
import ademar.study.test.core.model.HelloWorld
import ademar.study.test.injection.LifeCycleScope
import ademar.study.test.mapper.ErrorMapper
import ademar.study.test.mapper.HelloWorldMapper
import ademar.study.test.model.HelloWorldViewModel
import ademar.study.test.navigation.FlowController
import ademar.study.test.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@LifeCycleScope
class HomePresenter @Inject constructor(

        private val flowController: FlowController,
        private val getHelloWorlds: GetHelloWorlds,
        private val helloWorldMapper: HelloWorldMapper,
        private val errorMapper: ErrorMapper

) : BasePresenter<HomeView>() {

    private val map = linkedMapOf<HelloWorldViewModel, HelloWorld>()

    fun onStart() = loadData()

    fun onReloadClick() = loadData()

    fun onHelloWorldClick(focused: HelloWorldViewModel) = flowController.launchDetail(
            map[focused] ?: throw IllegalStateException("the map $map doesn't know the focused item $focused"), map.values.toList())

    private fun loadData() {
        view?.showLoading()
        view?.clearHelloWorlds()
        subscriptions.add(getHelloWorlds.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val viewModel = helloWorldMapper.transform(it)
                    map[viewModel] = it
                    view?.bindHelloWorld(viewModel)
                    view?.showContent()
                }, { e ->
                    view?.showError(errorMapper.transform(e))
                    view?.showRetry()
                }))
    }

}
