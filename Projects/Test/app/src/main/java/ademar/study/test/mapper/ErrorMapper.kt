package ademar.study.test.mapper

import ademar.study.test.R
import ademar.study.test.model.ErrorViewModel
import android.content.Context
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorMapper @Inject constructor(

        context: Context

) {

    private val noConnection = context.getString(R.string.error_message_no_connection)
    private val unauthorized = context.getString(R.string.error_message_unauthorized)
    private val unknown = context.getString(R.string.error_message_unknown)

    fun transform(throwable: Throwable?) = when (throwable) {
        null -> ErrorViewModel(unknown)
        is UnknownHostException -> ErrorViewModel(noConnection)
        is HttpException -> when (throwable.code()) {
            401 -> ErrorViewModel(unauthorized)
            else -> ErrorViewModel(throwable.message() ?: unknown)
        }
        else -> ErrorViewModel(throwable.message ?: unknown)
    }

}
