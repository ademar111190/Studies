package ademar.study.template.view.detail

import ademar.study.template.R
import ademar.study.template.injection.LifeCycleModule
import ademar.study.template.model.HelloWorldViewModel
import ademar.study.template.presenter.detail.DetailPresenter
import ademar.study.template.presenter.detail.DetailView
import ademar.study.template.view.base.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.detail_activity.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailView {

    @Inject lateinit var presenter: DetailPresenter

    override fun makeLifeCycleModule(): LifeCycleModule {
        return LifeCycleModule(getBaseActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        prepareTaskDescription()
        component.inject(this)
        presenter.onAttachView(this)
        reload.setOnClickListener { presenter.onReloadClick() }
        toolbar.setNavigationOnClickListener { back() }
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
            intent.setClassName(context, DetailActivity::class.java.name)
            return intent
        }

    }

}
