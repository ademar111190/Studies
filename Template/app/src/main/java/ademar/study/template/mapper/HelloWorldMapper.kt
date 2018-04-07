package ademar.study.template.mapper

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.model.HelloWorldViewModel
import javax.inject.Inject

class HelloWorldMapper @Inject constructor() {

    fun transform(helloWorld: HelloWorld) = HelloWorldViewModel(helloWorld.message, "https://upload.wikimedia.org/wikipedia/commons/${helloWorld.image}")

}
