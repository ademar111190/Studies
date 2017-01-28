package ademar.study.reddit.core.ext

import ademar.study.reddit.core.model.Error

fun Throwable?.asError(): Error {
    if (this is Error) {
        return this
    } else {
        val error = Error()
        error.message = this?.message ?: ""
        return error
    }
}
