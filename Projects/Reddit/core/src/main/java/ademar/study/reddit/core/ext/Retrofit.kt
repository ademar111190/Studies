package ademar.study.reddit.core.ext

import ademar.study.reddit.core.model.Error
import ademar.study.reddit.core.model.FieldProperty
import ademar.study.reddit.core.model.StandardErrors
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit

var Retrofit.standardErrors: StandardErrors by FieldProperty()

fun <T> Retrofit.observeBody(response: Response<T>): Observable<T> {
    try {
        when (response.code()) {
            200 -> {
                val body = response.body()
                return Observable.just(body)
            }
            401 -> {
                return Observable.error(standardErrors.UNAUTHORIZED)
            }
            else -> {
                val errorBody = response.errorBody()
                if (errorBody != null) {
                    val converter = responseBodyConverter<Error>(Error::class.java, arrayOf<Annotation>())
                    val error: Error = converter.convert(errorBody)
                    if (error.message.isNotEmpty()) {
                        return Observable.error(error)
                    }
                }
                return Observable.error(standardErrors.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        return Observable.error(e)
    }
}
