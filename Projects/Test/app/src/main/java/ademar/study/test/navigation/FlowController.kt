package ademar.study.test.navigation

import ademar.study.test.R
import ademar.study.test.core.model.HelloWorld
import ademar.study.test.view.base.BaseActivity
import ademar.study.test.view.detail.DetailActivity
import ademar.study.test.view.detail.DetailFragment
import ademar.study.test.view.home.HomeActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import javax.inject.Inject

class FlowController @Inject constructor(

        private val context: BaseActivity,
        private val intentFactory: IntentFactory

) {

    fun launchHome() = context.startActivity(intentFactory.makeIntent().apply {
        setClassName(context, HomeActivity::class.java.name)
    })

    fun launchDetail(detailActivity: DetailActivity) {
        launchDetailFragment(detailActivity.intent.extras)
    }

    fun launchDetail(focused: HelloWorld, others: List<HelloWorld>) {
        val bundle = bundleOf(ARG_FOCUSED to focused, ARG_OTHERS to others)
        if (context.resources.getBoolean(R.bool.large_screen)) {
            launchDetailFragment(bundle)
        } else {
            context.startActivity(intentFactory.makeIntent().apply {
                setClassName(context, DetailActivity::class.java.name)
                putExtras(bundle)
            })
        }
    }

    private fun launchDetailFragment(bundle: Bundle) {
        context.supportFragmentManager.beginTransaction()
                .replace(R.id.detail_fragment, DetailFragment().apply {
                    arguments = bundle
                })
                .commit()
    }

}

const val ARG_TITLE = "ARG_TITLE"
const val ARG_IMAGE = "ARG_IMAGE"
const val ARG_FOCUSED = "ARG_FOCUSED"
const val ARG_OTHERS = "ARG_OTHERS"
