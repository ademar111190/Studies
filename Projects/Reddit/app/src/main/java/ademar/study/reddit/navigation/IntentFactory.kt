package ademar.study.reddit.navigation

import android.content.Intent
import javax.inject.Inject

class IntentFactory @Inject constructor() {

    fun makeIntent(): Intent {
        return Intent()
    }

}
