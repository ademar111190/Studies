package ademar.study.test.core.interactor

import ademar.study.test.core.model.HelloWorld
import ademar.study.test.core.repository.HelloWorldRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetHelloWorlds @Inject constructor(

        private val repository: HelloWorldRepository

) {

    fun execute(): Observable<HelloWorld> = repository.getAllHelloWorld()

}
