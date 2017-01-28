package ademar.study.reddit.test

import ademar.study.reddit.core.model.Error
import ademar.study.reddit.core.model.HelloWorld
import ademar.study.reddit.model.ErrorViewModel
import ademar.study.reddit.model.home.HelloWorldViewModel

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
