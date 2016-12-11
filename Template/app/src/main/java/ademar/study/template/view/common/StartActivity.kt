package ademar.study.template.view.common

import ademar.study.template.navigation.FlowController
import ademar.study.template.view.base.BaseActivity
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
