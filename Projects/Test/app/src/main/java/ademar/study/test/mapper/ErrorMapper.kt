package ademar.study.test.mapper

import ademar.study.test.core.ext.asError
import ademar.study.test.model.ErrorViewModel
import javax.inject.Inject

class ErrorMapper @Inject constructor() {

    fun transform(throwable: Throwable): ErrorViewModel {
        val error = throwable.asError()
        return ErrorViewModel(error.code, error.message)
    }

}
