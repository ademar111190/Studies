package ademar.study.template.view.detail

import ademar.study.template.R
import ademar.study.template.navigation.FlowController
import ademar.study.template.view.base.BaseActivity
import android.os.Bundle
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    @Inject lateinit var flowController: FlowController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        prepareTaskDescription()
        component.inject(this)
        flowController.launchDetail(this)
    }

}
