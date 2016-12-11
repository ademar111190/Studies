package ademar.study.test.core.repository

import ademar.study.test.core.ext.observeBody
import ademar.study.test.core.model.HelloWorld
import ademar.study.test.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.test.core.repository.datasource.HelloWorldLocalRepository
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class HelloWorldRepository @Inject constructor(

        private val retrofit: Retrofit,
        private val cloud: HelloWorldCloudRepository,
        private val local: HelloWorldLocalRepository

) {

    fun getHelloWorld(): Observable<HelloWorld> {
        val cached = local.helloWorld
        if (cached != null) {
            return Observable.just(cached)
        } else {
            return cloud.getHelloWorld()
                    .flatMap { retrofit.observeBody(it) }
                    .doOnNext { local.helloWorld = it }
        }
    }

}
