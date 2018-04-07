package ademar.study.template.test

import ademar.study.template.core.model.HelloWorld
import ademar.study.template.model.DetailViewModel
import ademar.study.template.model.ErrorViewModel
import ademar.study.template.model.HelloWorldViewModel

object Fixture {

    const val MESSAGE = "Hello World!"
    const val IMAGE = "An Image"
    const val UNKNOWN = "UNKNOWN"
    const val UNAUTHORIZED = "UNAUTHORIZED"
    const val NO_CONNECTION = "NO_CONNECTION"

    fun error() = Error(MESSAGE)

    fun helloWorld() = HelloWorld(MESSAGE, IMAGE)

    fun errorViewModel() = ErrorViewModel(MESSAGE)

    fun helloWorldViewModel() = HelloWorldViewModel(MESSAGE, IMAGE)

    fun detailViewModel() = DetailViewModel(helloWorldViewModel(), listOf(helloWorldViewModel()))

}
