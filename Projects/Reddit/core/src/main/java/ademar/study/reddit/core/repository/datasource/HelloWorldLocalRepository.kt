package ademar.study.reddit.core.repository.datasource

import ademar.study.reddit.core.model.HelloWorld
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HelloWorldLocalRepository @Inject constructor() {

    var helloWorld: HelloWorld? = null

}
