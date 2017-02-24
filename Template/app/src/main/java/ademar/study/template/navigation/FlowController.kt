package ademar.study.template.navigation

import ademar.study.template.R
import ademar.study.template.view.base.BaseActivity
import ademar.study.template.view.detail.DetailActivity
import ademar.study.template.view.detail.DetailFragment
import ademar.study.template.view.home.HomeActivity
import javax.inject.Inject

class FlowController @Inject constructor(

        private val context: BaseActivity,
        private val intentFactory: IntentFactory

) {

    fun launchHome() {
        var intent = intentFactory.makeIntent()
        intent = HomeActivity.populateIntent(intent, context)
        context.startActivity(intent)
    }

    fun launchDetail() {
        if (context.resources.getBoolean(R.bool.large_screen)) {
            context.supportFragmentManager.beginTransaction()
                    .replace(R.id.detail_fragment, DetailFragment.newInstance())
                    .commit()
        } else {
            var intent = intentFactory.makeIntent()
            intent = DetailActivity.populateIntent(intent, context)
            context.startActivity(intent)
        }
    }

}
