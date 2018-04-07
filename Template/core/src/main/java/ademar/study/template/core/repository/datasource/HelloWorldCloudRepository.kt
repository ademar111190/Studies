package ademar.study.template.core.repository.datasource

import ademar.study.template.core.model.HelloWorld
import io.reactivex.Observable
import retrofit2.http.GET

interface HelloWorldCloudRepository {

    @GET("/hellos")
    fun getAllHelloWorld(): Observable<List<HelloWorld>>

}
