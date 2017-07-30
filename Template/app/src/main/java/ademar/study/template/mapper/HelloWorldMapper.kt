package ademar.study.template.mapper

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.core.model.StandardErrors
import ademar.study.template.model.HelloWorldViewModel
import javax.inject.Inject

class HelloWorldMapper @Inject constructor(

        private val standardErrors: StandardErrors

) {

    fun transform(helloWorld: HelloWorld) = HelloWorldViewModel(
            helloWorld.message ?: standardErrors.UNKNOWN.message,
            "https://developer.android.com/images/brand/Android_Robot_100.png")

}
