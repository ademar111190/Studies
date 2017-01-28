package ademar.study.reddit.core.repository.datasource

import ademar.study.reddit.core.model.HelloWorld
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface HelloWorldCloudRepository {

    @GET("/hello")
    fun getHelloWorld(): Observable<Response<HelloWorld>>

}
