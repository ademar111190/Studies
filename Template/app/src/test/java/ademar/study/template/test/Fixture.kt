package ademar.study.template.test

import ademar.study.template.core.model.Error
import ademar.study.template.core.model.HelloWorld
import ademar.study.template.model.ErrorViewModel
import ademar.study.template.model.HelloWorldViewModel

object Fixture {

    object error {

        val CODE = 1
        val MESSAGE = "Some error"

        fun makeModel(): Error {
            val model = Error()
            model.code = CODE
            model.message = MESSAGE
            return model
        }

    }

    object errorViewModel {

        val CODE = 1
        val MESSAGE = "Some error"

        fun makeModel(): ErrorViewModel {
            return ErrorViewModel(CODE, MESSAGE)
        }

    }

    object helloWorld {

        val MESSAGE = "Hello World!"

        fun makeModel(): HelloWorld {
            val model = HelloWorld()
            model.message = MESSAGE
            return model
        }

    }

    object helloWorldViewModel {

        val MESSAGE = "Hello World!"

        fun makeModel(): HelloWorldViewModel {
            return HelloWorldViewModel(MESSAGE)
        }

    }

}
