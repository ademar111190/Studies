package ademar.study.template.mapper

import ademar.study.template.core.model.StandardErrors
import ademar.study.template.model.ErrorViewModel
import javax.inject.Inject

class ErrorMapper @Inject constructor(

        private val standardErrors: StandardErrors

) {

    fun transform(throwable: Throwable): ErrorViewModel {
        val error = standardErrors.toError(throwable)
        return ErrorViewModel(error.code, error.message)
    }

}
