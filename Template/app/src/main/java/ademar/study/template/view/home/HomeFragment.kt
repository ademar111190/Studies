package ademar.study.template.view.home

import ademar.study.template.R
import ademar.study.template.model.HelloWorldViewModel
import ademar.study.template.presenter.home.HomePresenter
import ademar.study.template.presenter.home.HomeView
import ademar.study.template.view.base.BaseFragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeView {

    @Inject lateinit var presenter: HomePresenter
    @Inject lateinit var adapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater
            .inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        component.inject(this)
        presenter.onAttachView(this)

        reload.setOnClickListener { presenter.onReloadClick() }
        adapter.listener = { presenter.onHelloWorldClick() }
        listRefresh.setOnRefreshListener { presenter.onReloadClick() }

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDetachView()
    }

    override fun showLoading() {
        list.visibility = View.GONE
        load.visibility = View.VISIBLE
        reload.visibility = View.GONE
        listRefresh.isRefreshing = false
    }

    override fun showRetry() {
        list.visibility = View.GONE
        load.visibility = View.GONE
        reload.visibility = View.VISIBLE
        listRefresh.isRefreshing = false
    }

    override fun showContent() {
        list.visibility = View.VISIBLE
        load.visibility = View.GONE
        reload.visibility = View.GONE
        listRefresh.isRefreshing = false
    }

    override fun clearHelloWorlds() {
        adapter.clear()
    }

    override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
        adapter.addItem(viewModel)
    }

    companion object {

        fun newInstance() = HomeFragment()

    }

}
