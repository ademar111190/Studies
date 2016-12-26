package ademar.study.test.view.base

import ademar.study.test.App
import ademar.study.test.core.model.Error
import ademar.study.test.injection.DaggerLifeCycleComponent
import ademar.study.test.injection.LifeCycleComponent
import ademar.study.test.injection.LifeCycleModule
import ademar.study.test.presenter.LoadDataView
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
