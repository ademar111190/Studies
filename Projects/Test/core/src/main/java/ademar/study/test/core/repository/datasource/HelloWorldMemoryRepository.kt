package ademar.study.test.core.repository.datasource

import ademar.study.test.core.model.HelloWorld
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HelloWorldMemoryRepository @Inject constructor() {

    var hellos: List<HelloWorld>? = null

}
