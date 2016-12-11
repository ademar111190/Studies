package ademar.study.template.core.ext

import ademar.study.template.core.model.Error
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit

fun <T> Retrofit.observeBody(response: Response<T>): Observable<T> {
    try {
        when (response.code()) {
            200 -> {
                val body = response.body()
                return Observable.just(body)
            }
            401 -> {
                return Observable.error(Error.UNAUTHORIZED)
            }
            else -> {
                val errorBody = response.errorBody()
                if (errorBody != null) {
                    val converter = responseBodyConverter<Error>(Error::class.java, arrayOf<Annotation>())
                    val error: Error = converter.convert(errorBody)
                    return Observable.error(error)
                } else {
                    return Observable.error(Error.UNKNOWN)
                }
            }
        }
    } catch (e: Exception) {
        return Observable.error(e)
    }
}
