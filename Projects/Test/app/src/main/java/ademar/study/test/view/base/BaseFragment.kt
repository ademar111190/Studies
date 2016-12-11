package ademar.study.test.view.base

import ademar.study.test.App
import ademar.study.test.core.model.Error
import ademar.study.test.injection.component.DaggerFragmentComponent
import ademar.study.test.injection.component.FragmentComponent
import ademar.study.test.injection.module.FragmentModule
import ademar.study.test.presenter.LoadDataView
import android.support.v4.app.Fragment

open class BaseFragment : Fragment(), LoadDataView {

    val component: FragmentComponent by lazy {
        DaggerFragmentComponent.builder()
                .coreComponent(getApp().coreComponent)
                .fragmentModule(makeFragmentModule())
                .build()
    }

    protected open fun makeFragmentModule(): FragmentModule {
        return FragmentModule(getBaseFragment())
    }

    fun getApp(): App {
        return context.applicationContext as App
    }

    override fun getBaseActivity(): BaseActivity? {
        return activity as BaseActivity?
    }

    fun getBaseFragment(): BaseFragment {
        return this
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
