package ademar.study.test.test

import ademar.study.test.core.model.Error
import ademar.study.test.core.model.HelloWorld
import ademar.study.test.model.ErrorViewModel
import ademar.study.test.model.HelloWorldViewModel

object Fixture {

    object error {

        val CODE = 1
        val MESSAGE = "Some error"

        fun makeModel() = Error().apply {
            code = CODE
            message = MESSAGE
        }

    }

    object errorViewModel {

        val CODE = 1
        val MESSAGE = "Some error"

        fun makeModel() = ErrorViewModel(CODE, MESSAGE)

    }

    object helloWorld {

        val MESSAGE = "Hello World!"

        fun makeModel() = HelloWorld().apply {
            message = MESSAGE
        }

    }

    object helloWorldViewModel {

        val MESSAGE = "Hello World!"
        val IMAGE = "An Image"

        fun makeModel() = HelloWorldViewModel(MESSAGE, IMAGE)

    }

}
