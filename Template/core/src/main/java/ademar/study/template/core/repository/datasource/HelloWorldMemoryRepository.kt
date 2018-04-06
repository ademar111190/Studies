package ademar.study.template.core.repository.datasource

import ademar.study.template.core.model.HelloWorld
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HelloWorldMemoryRepository @Inject constructor() {

    var helloWorld: HelloWorld? = null
    var hellos: List<HelloWorld>? = null

}
