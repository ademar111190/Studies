package ademar.study.template.test

import ademar.study.template.core.model.Error
import ademar.study.template.core.model.HelloWorld
import ademar.study.template.model.ErrorViewModel
import ademar.study.template.model.HelloWorldViewModel

object Fixture {

    object error {

        val CODE = 1
        val MESSAGE = "Some error"

        fun makeModel() = Error(CODE, MESSAGE)

    }

    object errorViewModel {

        val CODE = 1
        val MESSAGE = "Some error"

        fun makeModel() = ErrorViewModel(CODE, MESSAGE)

    }

    object helloWorld {

        val MESSAGE = "Hello World!"

        fun makeModel() = HelloWorld(MESSAGE)

    }

    object helloWorldViewModel {

        val MESSAGE = "Hello World!"
        val IMAGE = "An Image"

        fun makeModel() = HelloWorldViewModel(MESSAGE, IMAGE)

    }

}
