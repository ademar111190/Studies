package ademar.study.reddit.navigation

import ademar.study.reddit.plataform.factories.IntentFactory
import ademar.study.reddit.view.base.BaseActivity
import ademar.study.reddit.view.home.HomeActivity
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
