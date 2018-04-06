package ademar.study.template.core.repository

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.template.core.repository.datasource.HelloWorldMemoryRepository
import io.reactivex.Observable
import javax.inject.Inject

class HelloWorldRepository @Inject constructor(

        private val cloud: HelloWorldCloudRepository,
        private val memory: HelloWorldMemoryRepository
        // TODO create the persistence repository

) {

    fun getHelloWorld(): Observable<HelloWorld> {
        val cached = memory.helloWorld
        return if (cached != null) {
            Observable.just(cached)
        } else {
            cloud.getHelloWorld().doOnNext { memory.helloWorld = it }
        }
    }

    fun getAllHelloWorld(): Observable<List<HelloWorld>> {
        val cached = memory.hellos
        return if (cached != null) {
            Observable.just(cached)
        } else {
            cloud.getAllHelloWorld().doOnNext { memory.hellos = it }
        }
    }

}
