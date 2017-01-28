package ademar.study.reddit.view.base

import ademar.study.reddit.App
import ademar.study.reddit.injection.DaggerLifeCycleComponent
import ademar.study.reddit.injection.LifeCycleComponent
import ademar.study.reddit.injection.LifeCycleModule
import ademar.study.reddit.model.ErrorViewModel
import ademar.study.reddit.presenter.LoadDataView
import android.support.v4.app.Fragment

open class BaseFragment : Fragment(), LoadDataView {

    val component: LifeCycleComponent by lazy {
        DaggerLifeCycleComponent.builder()
                .coreComponent(getApp().coreComponent)
                .lifeCycleModule(makeLifeCycleModule())
                .build()
    }

    protected open fun makeLifeCycleModule(): LifeCycleModule {
        return LifeCycleModule(getBaseActivity()!!)
    }

    fun getApp(): App {
        return context.applicationContext as App
    }

    override fun getBaseActivity(): BaseActivity? {
        return activity as BaseActivity?
    }

    override fun showLoading() {
    }

    override fun showRetry() {
    }

    override fun showError(viewModel: ErrorViewModel) {
        getBaseActivity()?.showError(viewModel)
    }

    override fun showContent() {
    }

}
