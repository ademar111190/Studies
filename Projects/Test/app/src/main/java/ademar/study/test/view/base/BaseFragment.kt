package ademar.study.test.view.base

import ademar.study.test.App
import ademar.study.test.injection.DaggerLifeCycleComponent
import ademar.study.test.injection.LifeCycleComponent
import ademar.study.test.injection.LifeCycleModule
import ademar.study.test.model.ErrorViewModel
import ademar.study.test.presenter.LoadDataView
import android.support.v4.app.Fragment

open class BaseFragment : Fragment(), LoadDataView {

    val component: LifeCycleComponent by lazy {
        DaggerLifeCycleComponent.builder()
                .coreComponent(getApp().coreComponent)
                .lifeCycleModule(makeLifeCycleModule())
                .build()
    }

    protected open fun makeLifeCycleModule() = LifeCycleModule(getBaseActivity()!!)

    fun getApp() = context!!.applicationContext as App

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
