package ademar.study.test.navigation

import ademar.study.test.R
import ademar.study.test.view.base.BaseActivity
import ademar.study.test.view.detail.DetailActivity
import ademar.study.test.view.detail.DetailFragment
import ademar.study.test.view.home.HomeActivity
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
