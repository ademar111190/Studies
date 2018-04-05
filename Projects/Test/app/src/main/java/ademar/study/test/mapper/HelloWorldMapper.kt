package ademar.study.test.mapper

import ademar.study.test.core.model.HelloWorld
import ademar.study.test.model.HelloWorldViewModel
import javax.inject.Inject

class HelloWorldMapper @Inject constructor() {

    fun transform(helloWorld: HelloWorld) = HelloWorldViewModel(helloWorld.message, "https://developer.android.com/images/brand/Android_Robot_100.png")

}
