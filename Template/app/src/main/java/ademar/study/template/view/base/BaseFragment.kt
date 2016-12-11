package ademar.study.template.view.base

import ademar.study.template.App
import ademar.study.template.core.model.Error
import ademar.study.template.injection.component.DaggerFragmentComponent
import ademar.study.template.injection.component.FragmentComponent
import ademar.study.template.injection.module.FragmentModule
import ademar.study.template.presenter.LoadDataView
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
