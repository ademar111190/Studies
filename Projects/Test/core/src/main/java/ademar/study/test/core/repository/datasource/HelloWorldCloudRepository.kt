package ademar.study.test.core.repository.datasource

import ademar.study.test.core.model.HelloWorld
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface HelloWorldCloudRepository {

    @GET("/hello")
    fun getHelloWorld(): Observable<Response<HelloWorld>>

    @GET("/hellos")
    fun getAllHelloWorld(): Observable<Response<List<HelloWorld>>>

}
