package ademar.study.reddit.view.common

import ademar.study.reddit.navigation.FlowController
import ademar.study.reddit.view.base.BaseActivity
import android.os.Bundle
import javax.inject.Inject

class StartActivity : BaseActivity() {

    @Inject lateinit var flowController: FlowController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        flowController.launchHome()
        finish()
    }

}
