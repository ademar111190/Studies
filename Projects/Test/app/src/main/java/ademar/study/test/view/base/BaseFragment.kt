package ademar.study.test.view.base

import ademar.study.test.App
import ademar.study.test.R
import ademar.study.test.injection.DaggerLifeCycleComponent
import ademar.study.test.injection.LifeCycleComponent
import ademar.study.test.injection.LifeCycleModule
import ademar.study.test.model.ErrorViewModel
import ademar.study.test.presenter.LoadDataView
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log

open class BaseFragment : Fragment(), LoadDataView {

    val component: LifeCycleComponent by lazy {
        DaggerLifeCycleComponent.builder()
                .coreComponent(getApp().coreComponent)
                .lifeCycleModule(makeLifeCycleModule())
                .build()
    }

    protected open fun makeLifeCycleModule() = LifeCycleModule(getBaseActivity())

    private fun getApp(): App {
        val context = context?.applicationContext
        return when (context) {
            is App -> context
            else -> throw IllegalStateException("BaseFragment $this needs to be used with an App context, current: $context")
        }
    }

    override fun getBaseActivity(): BaseActivity {
        val activity = activity
        return when (activity) {
            is BaseActivity -> activity
            else -> throw IllegalStateException("BaseFragment $this needs to be used with a BaseActivity, current: $activity")
        }
    }

    override fun showLoading() {
    }

    override fun showRetry() {
    }

    override fun showError(viewModel: ErrorViewModel) {
        val activity = activity
        if (activity == null) {
            Log.w("BaseFragment", "showError called with $viewModel but the activity is null. Skipping.")
            return
        }
        AlertDialog.Builder(activity, R.style.AppAlertDialog)
                .setMessage(viewModel.message)
                .setPositiveButton(R.string.app_ok, null)
                .create()
                .show()
    }

    override fun showContent() {
    }

}
