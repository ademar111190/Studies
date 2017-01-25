package ademar.study.test.core.model

import ademar.study.test.core.R
import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StandardErrors @Inject constructor(

        private val context: Context

) {

    val UNKNOWN by lazy {
        Error().apply {
            code = 10001
            message = context.getString(R.string.error_message_unknown)
        }
    }

    val UNAUTHORIZED by lazy {
        Error().apply {
            code = 10002
            message = context.getString(R.string.error_message_unauthorized)
        }
    }

}
