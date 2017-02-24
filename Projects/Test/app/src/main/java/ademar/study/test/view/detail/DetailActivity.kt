package ademar.study.test.view.detail

import ademar.study.test.R
import ademar.study.test.view.base.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        prepareTaskDescription()
    }

    companion object {

        fun populateIntent(intent: Intent, context: Context): Intent {
            intent.setClassName(context, DetailActivity::class.java.name)
            return intent
        }

    }

}
