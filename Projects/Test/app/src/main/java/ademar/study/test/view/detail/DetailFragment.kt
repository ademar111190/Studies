package ademar.study.test.view.detail

import ademar.study.test.R
import ademar.study.test.injection.GlideApp
import ademar.study.test.model.HelloWorldViewModel
import ademar.study.test.presenter.detail.DetailPresenter
import ademar.study.test.presenter.detail.DetailView
import ademar.study.test.view.base.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.detail_fragment.*
import javax.inject.Inject

class DetailFragment : BaseFragment(), DetailView {

    @Inject lateinit var presenter: DetailPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        component.inject(this)
        presenter.onAttachView(this)

        reload.setOnClickListener { presenter.onReloadClick() }
        if (!context.resources.getBoolean(R.bool.large_screen)) {
            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener { getBaseActivity()?.back() }
        }
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
        content.visibility = GONE
        load.visibility = VISIBLE
        reload.visibility = GONE
    }

    override fun showRetry() {
        content.visibility = GONE
        load.visibility = GONE
        reload.visibility = VISIBLE
    }

    override fun showContent() {
        content.visibility = VISIBLE
        load.visibility = GONE
        reload.visibility = GONE
    }

    override fun bindHelloWorld(viewModel: HelloWorldViewModel) {
        text.text = viewModel.message

        GlideApp.with(this)
                .load(viewModel.image)
                .centerInside()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(image)
    }

    companion object {

        fun newInstance() = DetailFragment()

    }

}
