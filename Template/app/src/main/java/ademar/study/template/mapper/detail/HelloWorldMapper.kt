package ademar.study.template.mapper.detail

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.core.model.StandardErrors
import ademar.study.template.model.detail.HelloWorldViewModel
import javax.inject.Inject

class HelloWorldMapper @Inject constructor(

        private val standardErrors: StandardErrors

) {

    fun transform(helloWorld: HelloWorld): HelloWorldViewModel {
        return HelloWorldViewModel(helloWorld.message ?: standardErrors.UNKNOWN.message)
    }

}
