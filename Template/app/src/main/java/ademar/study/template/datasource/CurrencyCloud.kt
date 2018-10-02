package ademar.study.template.datasource

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyCloud {

    @GET("currencies")
    fun getCurrencies(): Observable<Any>

    @GET("currency/{id}")
    fun getCurrency(
            @Path("id") id: String
    ): Observable<Any>

}
