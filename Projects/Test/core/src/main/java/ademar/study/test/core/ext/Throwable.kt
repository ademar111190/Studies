package ademar.study.test.core.ext

import ademar.study.test.core.model.Error

fun Throwable?.asError(): Error {
    if (this is Error) {
        return this
    } else {
        val error = Error()
        error.message = this?.message ?: ""
        return error
    }
}
