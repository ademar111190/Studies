package ademar.study.template.mapper

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.model.HelloWorldViewModel
import javax.inject.Inject

class HelloWorldMapper @Inject constructor() {

    fun transform(helloWorld: HelloWorld) = HelloWorldViewModel(helloWorld.message,
            "https://upload.wikimedia.org/wikipedia/commons/9/90/PIA18089-MarsCuriosityRover-WindjanaRockDrilling-20140429.jpg")

}
