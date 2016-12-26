package ademar.study.template.view.base

import ademar.study.template.App
import ademar.study.template.core.model.Error
import ademar.study.template.injection.DaggerLifeCycleComponent
import ademar.study.template.injection.LifeCycleComponent
import ademar.study.template.injection.LifeCycleModule
import ademar.study.template.presenter.LoadDataView
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

    override fun showError(error: Error) {
        getBaseActivity()?.showError(error)
    }

    override fun showContent() {
    }

}
