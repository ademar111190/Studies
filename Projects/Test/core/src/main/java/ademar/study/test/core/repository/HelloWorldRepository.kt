package ademar.study.test.core.repository

import ademar.study.test.core.model.HelloWorld
import ademar.study.test.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.test.core.repository.datasource.HelloWorldMemoryRepository
import io.reactivex.Observable
import javax.inject.Inject

class HelloWorldRepository @Inject constructor(

        private val cloud: HelloWorldCloudRepository,
        private val memory: HelloWorldMemoryRepository
        // TODO create the persistence repository

) {

    fun getAllHelloWorld(): Observable<HelloWorld> {
        val cached = memory.hellos
        val fromCloud = cloud.getAllHelloWorld()
                .doOnNext { memory.hellos = it }
                .flatMapIterable { it }
        return if (cached == null) {
            fromCloud
        } else {
            Observable.fromIterable(cached).mergeWith(fromCloud
                    .onErrorResumeNext(Observable.empty())
                    .filter { !cached.contains(it) })
        }
    }

}
