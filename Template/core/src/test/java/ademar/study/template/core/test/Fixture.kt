package ademar.study.template.core.test

import ademar.study.template.core.model.HelloWorld

object Fixture {

    const val MESSAGE = "Hello World!"

    fun error() = Error(MESSAGE)

    fun helloWorld() = HelloWorld(MESSAGE)

}

