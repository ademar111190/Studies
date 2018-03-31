package ademar.study.template.core.test

import ademar.study.template.core.model.Error
import ademar.study.template.core.model.HelloWorld

object Fixture {

    object error {

        val CODE = 1
        val MESSAGE = "Some error"
        val JSON = """
        |{
        |    "code": $CODE,
        |    "message": "$MESSAGE"
        |}
        """.trimMargin()

        fun makeException(): Throwable = Exception("Some error")

        fun makeModel() = Error(CODE, MESSAGE)

    }

    object hellos {

        val JSON = """
        |[${helloWorld.JSON}]
        """.trimMargin()

        fun makeModel() = listOf(helloWorld.makeModel())

    }

    object helloWorld {

        val MESSAGE = "Hello World!"
        val JSON = """
        |{
        |    "message": "$MESSAGE"
        |}
        """.trimMargin()

        fun makeModel() = HelloWorld(MESSAGE)

    }

}
