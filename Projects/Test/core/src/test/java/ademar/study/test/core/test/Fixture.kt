package ademar.study.test.core.test

import ademar.study.test.core.model.HelloWorld

object Fixture {

    const val MESSAGE = "Hello World!"
    const val IMAGE = "image.jpeg"

    fun error() = Error(MESSAGE)

    fun helloWorld() = HelloWorld(MESSAGE, IMAGE)

}
