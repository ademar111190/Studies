package ademar.study.reddit.core.interactor

import ademar.study.reddit.core.model.HelloWorld
import ademar.study.reddit.core.repository.HelloWorldRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetHelloWorldUseCase @Inject constructor(

        private val repository: HelloWorldRepository

) {

    fun execute(): Observable<HelloWorld> {
        return repository.getHelloWorld()
    }

}
