package ademar.study.template.view.home

import ademar.study.template.R
import ademar.study.template.injection.LifeCycleModule
import ademar.study.template.model.HelloWorldViewModel
import ademar.study.template.presenter.home.HomePresenter
import ademar.study.template.presenter.home.HomeView
import ademar.study.template.view.base.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.home_activity.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

    @Inject lateinit var presenter: HomePresenter
    @Inject lateinit var adapter: HomeAdapter

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
        adapter.listener = { presenter.onHelloWorldClick() }

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
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
        list.visibility = GONE
        load.visibility = VISIBLE
        reload.visibility = GONE
    }

    override fun showRetry() {
        list.visibility = GONE
        load.visibility = GONE
        reload.visibility = VISIBLE
    }

    override fun showContent() {
        list.visibility = VISIBLE
        load.visibility = GONE
        reload.visibility = GONE
    }

    override fun clearHelloWorlds() {
        adapter.clear()
    }

    override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
        adapter.addItem(viewModel)
    }

    companion object {

        fun populateIntent(intent: Intent, context: Context): Intent {
            intent.setClassName(context, HomeActivity::class.java.name)
            return intent
        }

    }

}
