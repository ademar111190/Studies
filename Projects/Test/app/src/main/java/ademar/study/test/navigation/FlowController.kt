package ademar.study.test.navigation

import ademar.study.test.view.base.BaseActivity
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

}
