package ademar.study.test.view.base

import ademar.study.test.App
import ademar.study.test.R
import ademar.study.test.core.model.Error
import ademar.study.test.injection.component.ActivityComponent
import ademar.study.test.injection.component.DaggerActivityComponent
import ademar.study.test.injection.module.ActivityModule
import ademar.study.test.presenter.LoadDataView
import android.app.ActivityManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), LoadDataView {

    protected val component: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .coreComponent(getApp().coreComponent)
                .activityModule(makeActivityModule())
                .build()
    }

    protected open fun makeActivityModule(): ActivityModule {
        return ActivityModule(this)
    }

    protected fun prepareTaskDescription(
            label: String = getString(R.string.app_name),
            icon: Int = R.drawable.ic_task,
            colorPrimary: Int = ContextCompat.getColor(this, R.color.primary)
    ) {
        val drawable = ContextCompat.getDrawable(this, icon)
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        setTaskDescription(ActivityManager.TaskDescription(label, bitmap, colorPrimary))
    }

    fun getApp(): App {
        return applicationContext as App
    }

    override fun getBaseActivity(): BaseActivity {
        return this
    }

    override fun showLoading() {
    }

    override fun showRetry() {
    }

    override fun showContent() {
    }

    override fun showError(error: Error) {
        AlertDialog.Builder(this, R.style.AppAlertDialog)
                .setMessage(error.message)
                .setPositiveButton(R.string.app_ok, null)
                .create()
                .show()
    }

}
