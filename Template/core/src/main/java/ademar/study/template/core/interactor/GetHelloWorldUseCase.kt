package ademar.study.template.core.interactor

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.core.repository.HelloWorldRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetHelloWorldUseCase @Inject constructor(

        private val repository: HelloWorldRepository

) {

    fun execute(): Observable<HelloWorld> {
        return repository.getHelloWorld()
    }

}
