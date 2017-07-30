package ademar.study.template.core.model

import ademar.study.template.core.R
import android.content.Context
import android.support.annotation.StringRes
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StandardErrors @Inject constructor(

        private val context: Context

) {

    val UNKNOWN by lazy { generate(10_001, R.string.error_message_unknown) }
    val UNAUTHORIZED by lazy { generate(10_002, R.string.error_message_unauthorized) }
    val NO_CONNECTION by lazy { generate(10_003, R.string.error_message_no_connection) }

    fun toError(throwable: Throwable?) = when (throwable) {
        is Error -> throwable
        is UnknownHostException -> NO_CONNECTION
        else -> {
            val message = throwable?.message
            if (message != null) {
                val error = Error()
                error.message = message
                error
            } else {
                UNKNOWN
            }
        }
    }

    private fun generate(code: Int, @StringRes message: Int) = Error().apply {
        this.code = code
        this.message = context.getString(message)
    }

}
