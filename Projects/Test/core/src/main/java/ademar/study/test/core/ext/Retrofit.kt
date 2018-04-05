package ademar.study.test.core.ext

import ademar.study.test.core.model.Error
import ademar.study.test.core.model.FieldProperty
import ademar.study.test.core.model.StandardErrors
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit

var Retrofit.standardErrors: StandardErrors by FieldProperty()

fun <T> Retrofit.observeBody(response: Response<T>): Observable<T> = try {
    when (response.code()) {
        200 -> {
            val body = response.body()
            if (body != null) {
                Observable.just(body)
            } else {
                Observable.empty()
            }
        }
        401 -> Observable.error(standardErrors.UNAUTHORIZED.toThrowable())
        else -> {
            val errorBody = response.errorBody()
            if (errorBody != null) {
                val converter = responseBodyConverter<Error>(Error::class.java, arrayOf<Annotation>())
                val error: Error? = converter.convert(errorBody)
                if (error?.message?.isNotEmpty() == true) {
                    Observable.error(error.toThrowable())
                } else {
                    Observable.error(standardErrors.UNKNOWN.toThrowable())
                }
            } else {
                Observable.error(standardErrors.UNKNOWN.toThrowable())
            }
        }
    }
} catch (e: Exception) {
    Observable.error(e)
}
