package ademar.study.template.navigation

import ademar.study.template.view.base.BaseActivity
import ademar.study.template.view.detail.DetailActivity
import javax.inject.Inject

class FlowController @Inject constructor(

        private val context: BaseActivity,
        private val intentFactory: IntentFactory

) {

    fun launchHome() {
        var intent = intentFactory.makeIntent()
        intent = DetailActivity.populateIntent(intent, context)
        context.startActivity(intent)
    }

}
