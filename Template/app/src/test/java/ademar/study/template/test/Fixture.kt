package ademar.study.template.test

import ademar.study.template.core.model.Error
import ademar.study.template.core.model.HelloWorld

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

    object helloWorld {

        val MESSAGE = "Hello World!"

        fun makeModel(): HelloWorld {
            val model = HelloWorld()
            model.message = MESSAGE
            return model
        }

    }

}
