package ademar.study.template.view.base

import ademar.study.template.App
import ademar.study.template.injection.DaggerLifeCycleComponent
import ademar.study.template.injection.LifeCycleComponent
import ademar.study.template.injection.LifeCycleModule
import ademar.study.template.model.ErrorViewModel
import ademar.study.template.presenter.LoadDataView
import android.support.v4.app.Fragment

open class BaseFragment : Fragment(), LoadDataView {

    val component: LifeCycleComponent by lazy {
        DaggerLifeCycleComponent.builder()
                .coreComponent(getApp().coreComponent)
                .lifeCycleModule(makeLifeCycleModule())
                .build()
    }

    protected open fun makeLifeCycleModule() = LifeCycleModule(getBaseActivity()!!)

    fun getApp() = context.applicationContext as App

    override fun getBaseActivity() = activity as BaseActivity?

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
