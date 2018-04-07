package ademar.study.template.view.detail

import ademar.study.template.R
import ademar.study.template.injection.GlideApp
import ademar.study.template.injection.LifeCycleModule
import ademar.study.template.model.DetailViewModel
import ademar.study.template.navigation.ARG_FOCUSED
import ademar.study.template.navigation.ARG_OTHERS
import ademar.study.template.presenter.detail.DetailPresenter
import ademar.study.template.presenter.detail.DetailView
import ademar.study.template.view.base.BaseFragment
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        component.inject(this)
        presenter.onAttachView(this)

        if (context?.resources?.getBoolean(R.bool.large_screen) != true) {
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

    override fun makeLifeCycleModule() = LifeCycleModule(
            getBaseActivity(),
            focused = arguments?.getParcelable(ARG_FOCUSED) ?: throw IllegalStateException("Unable to found the focused at arguments: $arguments"),
            others = arguments?.getParcelableArrayList(ARG_OTHERS) ?: throw IllegalStateException("Unable to found the others at arguments: $arguments"))

    override fun showLoading() {
        content.visibility = GONE
        load.visibility = VISIBLE
    }

    override fun showRetry() {
        content.visibility = GONE
        load.visibility = GONE
    }

    override fun showContent() {
        content.visibility = VISIBLE
        load.visibility = GONE
    }

    override fun bindDetail(viewModel: DetailViewModel) {
        text.text = viewModel.currentItem.message

        GlideApp.with(this)
                .load(viewModel.currentItem.image)
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
    }


}
