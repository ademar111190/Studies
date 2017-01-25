package ademar.study.template.mapper

import ademar.study.template.core.ext.asError
import ademar.study.template.model.ErrorViewModel
import javax.inject.Inject

class ErrorMapper @Inject constructor() {

    fun transform(throwable: Throwable): ErrorViewModel {
        val error = throwable.asError()
        return ErrorViewModel(error.code, error.message)
    }

}
