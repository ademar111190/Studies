package ademar.study.template.view.home

import ademar.study.template.R
import ademar.study.template.view.base.BaseActivity
import android.os.Bundle

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        prepareTaskDescription()

        supportFragmentManager.beginTransaction()
                .replace(R.id.home_fragment, HomeFragment())
                .commit()
    }

}
