package ademar.study.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> message.setText(R.string.title_home)
                R.id.navigation_dashboard -> message.setText(R.string.title_dashboard)
                R.id.navigation_notifications -> message.setText(R.string.title_notifications)
                else -> null
            } != null
        }
    }

}
