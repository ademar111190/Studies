package study.template

import android.app.Activity
import android.os.Bundle
import androidx.ui.core.setContent
import study.template.composable.AppInfo
import study.template.composable.AppInfoData

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppInfo(AppInfoData("${AppData.name()}\nVersion${AppData.version()}"))
        }
    }

}
