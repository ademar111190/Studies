package ademar.study.test.core.repository.datasource

import ademar.study.test.core.model.HelloWorld
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HelloWorldLocalRepository @Inject constructor() {

    var helloWorld: HelloWorld? = null

}
