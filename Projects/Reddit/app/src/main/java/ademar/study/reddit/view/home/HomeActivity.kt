package ademar.study.reddit.view.home

import ademar.study.reddit.R
import ademar.study.reddit.injection.LifeCycleModule
import ademar.study.reddit.model.home.HelloWorldViewModel
import ademar.study.reddit.presenter.home.HomePresenter
import ademar.study.reddit.presenter.home.HomeView
import ademar.study.reddit.view.base.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.home_activity.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

    @Inject lateinit var presenter: HomePresenter

    override fun makeLifeCycleModule(): LifeCycleModule {
        return LifeCycleModule(getBaseActivity())
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

    override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
        text.text = viewModel.message
    }

    companion object {

        fun populateIntent(intent: Intent, context: Context): Intent {
            intent.setClassName(context, HomeActivity::class.java.name)
            return intent
        }

    }

}
