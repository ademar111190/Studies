package ademar.study.template.core.repository.datasource

import ademar.study.template.core.model.HelloWorld
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HelloWorldLocalRepository @Inject constructor() {

    var helloWorld: HelloWorld? = null

}
