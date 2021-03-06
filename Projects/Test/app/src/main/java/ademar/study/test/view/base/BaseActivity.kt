package ademar.study.test.view.base

import ademar.study.test.App
import ademar.study.test.R
import ademar.study.test.injection.DaggerLifeCycleComponent
import ademar.study.test.injection.LifeCycleComponent
import ademar.study.test.injection.LifeCycleModule
import ademar.study.test.model.ErrorViewModel
import ademar.study.test.presenter.LoadDataView
import android.app.ActivityManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), LoadDataView {

    protected val component: LifeCycleComponent by lazy {
        DaggerLifeCycleComponent.builder()
                .coreComponent(getApp().coreComponent)
                .lifeCycleModule(makeLifeCycleModule())
                .build()
    }

    protected open fun makeLifeCycleModule() = LifeCycleModule(this)

    protected fun prepareTaskDescription(
            label: String = getString(R.string.app_name),
            icon: Int = R.drawable.ic_task,
            colorPrimary: Int = ContextCompat.getColor(this, R.color.primary)
    ) {
        val drawable = ContextCompat.getDrawable(this, icon)
        if (drawable != null) {
            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            setTaskDescription(ActivityManager.TaskDescription(label, bitmap, colorPrimary))
        }
    }

    fun getApp() = applicationContext as App

    override fun getBaseActivity() = this

    override fun showLoading() {
    }

    override fun showRetry() {
    }

    override fun showContent() {
    }

    override fun showError(viewModel: ErrorViewModel) = AlertDialog
            .Builder(this, R.style.AppAlertDialog)
            .setMessage(viewModel.message)
            .setPositiveButton(R.string.app_ok, null)
            .create()
            .show()

    fun back() {
        val upIntent = NavUtils.getParentActivityIntent(this) ?: intent
        if (NavUtils.shouldUpRecreateTask(this, upIntent) || isTaskRoot) {
            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(upIntent)
                    .startActivities()
        } else {
            NavUtils.navigateUpTo(this, upIntent)
        }
    }

}
