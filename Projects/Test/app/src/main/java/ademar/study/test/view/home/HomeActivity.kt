package ademar.study.test.view.home

import ademar.study.test.R
import ademar.study.test.core.model.HelloWorld
import ademar.study.test.injection.module.ActivityModule
import ademar.study.test.presenter.home.HomePresenter
import ademar.study.test.presenter.home.HomeView
import ademar.study.test.view.base.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.home_activity.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

    @Inject lateinit var presenter: HomePresenter

    override fun makeActivityModule(): ActivityModule {
        return ActivityModule(getBaseActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        prepareTaskDescription()
        component.inject(this)
        presenter.onAttachView(this)
        reload.setOnClickListener { presenter.onReloadClick() }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun showLoading() {
        text.visibility = GONE
        load.visibility = VISIBLE
        reload.visibility = GONE
    }

    override fun showRetry() {
        text.visibility = GONE
        load.visibility = GONE
        reload.visibility = VISIBLE
    }

    override fun showContent() {
        text.visibility = VISIBLE
        load.visibility = GONE
        reload.visibility = GONE
    }

    override fun bindHelloWorld(helloWorld: HelloWorld) {
        text.text = helloWorld.message
    }

    companion object {

        fun populateIntent(intent: Intent, context: Context): Intent {
            intent.setClassName(context, HomeActivity::class.java.name)
            return intent
        }

    }

}
