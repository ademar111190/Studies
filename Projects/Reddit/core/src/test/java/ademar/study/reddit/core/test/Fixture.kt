package ademar.study.reddit.core.test

import ademar.study.reddit.core.model.Error
import ademar.study.reddit.core.model.HelloWorld
import org.mockito.Mockito.`when` as whenever

object Fixture {

    object error {

        val CODE = 1
        val MESSAGE = "Some error"
        val JSON = """
        {
            "code": $CODE,
            "message": "$MESSAGE"
        }
        """

        fun makeException(): Throwable {
            return Exception("Some error")
        }

        fun makeModel(): Error {
            val model = Error()
            model.code = CODE
            model.message = MESSAGE
            return model
        }

    }

    object helloWorld {

        val MESSAGE = "Hello World!"
        val JSON = """
        {
            "message": "$MESSAGE"
        }
        """

        fun makeModel(): HelloWorld {
            val model = HelloWorld()
            model.message = MESSAGE
            return model
        }

    }

}
