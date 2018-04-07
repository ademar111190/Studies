package ademar.study.test.test

import ademar.study.test.core.model.HelloWorld
import ademar.study.test.model.DetailViewModel
import ademar.study.test.model.ErrorViewModel
import ademar.study.test.model.HelloWorldViewModel

object Fixture {

    const val INDEX = 1
    const val MESSAGE = "Hello World!"
    const val IMAGE = "An Image"
    const val UNKNOWN = "UNKNOWN"
    const val UNAUTHORIZED = "UNAUTHORIZED"
    const val NO_CONNECTION = "NO_CONNECTION"

    fun error() = Error(MESSAGE)

    fun helloWorld() = HelloWorld(MESSAGE, IMAGE)

    fun errorViewModel() = ErrorViewModel(MESSAGE)

    fun helloWorldViewModel() = HelloWorldViewModel(MESSAGE, IMAGE)

    fun detailViewModel() = DetailViewModel(INDEX, helloWorldViewModel(), listOf(helloWorldViewModel()))

}
