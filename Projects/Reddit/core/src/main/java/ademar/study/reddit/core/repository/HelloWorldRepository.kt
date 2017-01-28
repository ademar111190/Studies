package ademar.study.reddit.core.repository

import ademar.study.reddit.core.ext.observeBody
import ademar.study.reddit.core.model.HelloWorld
import ademar.study.reddit.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.reddit.core.repository.datasource.HelloWorldLocalRepository
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
