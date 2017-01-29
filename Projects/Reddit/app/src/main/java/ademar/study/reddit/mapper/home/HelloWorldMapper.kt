package ademar.study.reddit.mapper.home

import ademar.study.reddit.core.model.HelloWorld
import ademar.study.reddit.core.model.StandardErrors
import ademar.study.reddit.model.home.HelloWorldViewModel
import javax.inject.Inject

class HelloWorldMapper @Inject constructor(

        private val standardErrors: StandardErrors

) {

    fun transform(helloWorld: HelloWorld): HelloWorldViewModel {
        return HelloWorldViewModel(helloWorld.message ?: standardErrors.UNKNOWN.message)
    }

}
