package ademar.study.test.mapper

import ademar.study.test.core.model.StandardErrors
import ademar.study.test.model.ErrorViewModel
import javax.inject.Inject

class ErrorMapper @Inject constructor(

        private val standardErrors: StandardErrors

) {

    fun transform(throwable: Throwable): ErrorViewModel {
        val error = standardErrors.toError(throwable)
        return ErrorViewModel(error.code, error.message)
    }

}
