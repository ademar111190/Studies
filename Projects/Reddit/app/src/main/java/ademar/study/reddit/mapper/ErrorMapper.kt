package ademar.study.reddit.mapper

import ademar.study.reddit.core.ext.asError
import ademar.study.reddit.model.ErrorViewModel
import javax.inject.Inject

class ErrorMapper @Inject constructor() {

    fun transform(throwable: Throwable): ErrorViewModel {
        val error = throwable.asError()
        return ErrorViewModel(error.code, error.message)
    }

}
