package ademar.study.test.view.detail

import ademar.study.test.R
import ademar.study.test.navigation.FlowController
import ademar.study.test.view.base.BaseActivity
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
