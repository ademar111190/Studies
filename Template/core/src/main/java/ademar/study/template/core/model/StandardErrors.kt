package ademar.study.template.core.model

import ademar.study.template.core.R
import ademar.study.template.core.injection.ApplicationJsonAdapterFactory
import android.content.Context
import android.support.annotation.StringRes
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
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

    fun toError(throwable: Throwable?): Error {
        if (throwable == null) return UNKNOWN
        if (throwable is UnknownHostException) return NO_CONNECTION
        val message = throwable.message ?: return UNKNOWN
        if (message.matches("""\{ "code": [a-zA-Z0-9][a-zA-Z0-9]*, "message": ".*" \}""".toRegex())) {
            val fromJson = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .add(ApplicationJsonAdapterFactory.INSTANCE)
                    .build()
                    .adapter(Error::class.java)
                    .fromJson(message)
            if (fromJson != null) return fromJson
        }
        return Error(0, message)
    }

    private fun generate(code: Int, @StringRes message: Int) = Error(code, context.getString(message))

}
