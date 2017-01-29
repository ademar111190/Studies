package ademar.study.reddit.test

import ademar.study.reddit.core.model.Error
import ademar.study.reddit.core.model.HelloWorld
import ademar.study.reddit.core.model.Post
import ademar.study.reddit.model.ErrorViewModel
import ademar.study.reddit.model.home.HelloWorldViewModel
import ademar.study.reddit.model.home.PostViewModel

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

    object post {

        val TITLE = ""
        val AUTHOR = ""
        val THUMBNAIL = ""
        val CREATED = 1485666124L
        val COMMENTS = 7L
        val DOWNS = 1L
        val UPS = 3L

        fun makeModel(): Post {
            val model = Post()
            model.title = TITLE
            model.author = AUTHOR
            model.thumbnail = THUMBNAIL
            model.created = CREATED
            model.comments = COMMENTS
            model.downs = DOWNS
            model.ups = UPS
            return model
        }

    }

    object postViewModel {

        val TITLE = ""
        val AUTHOR = ""
        val THUMBNAIL = ""
        val CREATED = 1485666124L
        val COMMENTS = 7L
        val DOWNS = 1L
        val UPS = 3L

        fun makeModel(): PostViewModel {
            return PostViewModel(TITLE, AUTHOR, THUMBNAIL, CREATED, COMMENTS, DOWNS, UPS)
        }

    }

}
